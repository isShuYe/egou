package com.ego.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.CookieUtils;
import com.ego.commons.ItemStatus;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbUser;
import com.ego.provider.service.RegisterService;
import com.ego.redis.jedisdao.JedisClusterDao;
import com.ego.sso.service.SsoRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Service
public class SsoRegisterServiceImpl implements SsoRegisterService {
    @Reference
    private RegisterService registerService;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 验证用户唯一性
     * @param name
     * @param type
     * @return
     */
    @Override
    public ItemStatus userYanZhang(String name, Integer type) {
        return registerService.yanZheng(name,type);
    }

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @Override
    public ItemStatus register(TbUser tbUser) {
        Date date = new Date();
        tbUser.setCreated(date);
        tbUser.setUpdated(date);
        //对密码加密
        String password = tbUser.getPassword();
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        tbUser.setPassword(s);

        int i = registerService.register(tbUser);
        ItemStatus is = new ItemStatus();
        if (i>0){
            is.setStatus(200);
        }
        return is;
    }

    @Override
    public ItemStatus login(TbUser tbUser, HttpServletRequest req, HttpServletResponse resp) {
        ItemStatus is = new ItemStatus();
        try {
            TbUser user = registerService.login(tbUser);
            if(user!=null){
                //对密码进行加密验证
                String password = tbUser.getPassword();
                String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
                if(md5.equals(user.getPassword())){
                    //要存到redis中的session数据
                    String sessionKey = UUID.randomUUID().toString();
                    String sessionValue = JsonUtils.objectToJson(user);
                    jedisCluster.set(sessionKey,sessionValue);
                    //登录状态失效的时间
                    jedisCluster.expire(sessionKey,1800);

                    //将登录状态存储在redis中
                    CookieUtils.setCookie(req,resp,"TT_TOKEN",sessionKey,1800);

                    is.setStatus(200);
                    is.setData(sessionKey);
                    is.setMsg("登录成功");
                }else{
                    is.setStatus(400);
                    is.setData(null);
                    is.setMsg("输入的数据有误");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 登录状态
     * @param token
     * @return
     */
    @Override
    public ItemStatus loginMsg(String token, HttpServletRequest req) {
        ItemStatus is = new ItemStatus();
        String s = jedisCluster.get(token);
        if(!StringUtils.isEmpty(s)){
            TbUser tbUser = JsonUtils.jsonToPojo(s, TbUser.class);
            is.setStatus(200);
            is.setData(tbUser);
            is.setMsg("ok");
        }else {
            is.setStatus(400);
            is.setData(null);
            is.setMsg("登录状态失效或不存在");
        }
        return is;
    }

    /**
     * 用户退出
     * @param sessionKey
     * @return
     */
    @Override
    public ItemStatus logout(String sessionKey) {
        ItemStatus is = new ItemStatus();
        String s = jedisCluster.get(sessionKey);
        if(!StringUtils.isEmpty(s)){
            Long del = jedisCluster.del(sessionKey);
            if(!del.equals(0)){
                is.setStatus(200);
                is.setMsg("ok");
                is.setData("");
                return is;
            }
        }
        is.setData(null);
        is.setStatus(400);
        is.setMsg("登录用户不存在");
        return is;
    }


}
