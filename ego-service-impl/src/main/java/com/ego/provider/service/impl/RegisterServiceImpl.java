package com.ego.provider.service.impl;

import com.ego.commons.ItemStatus;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
import com.ego.provider.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper tbUserMapper;

    //验证用户名唯一性
    @Override
    public ItemStatus yanZheng(String name, Integer type) {
        TbUserExample example = new TbUserExample();
        ItemStatus is = new ItemStatus();
        try {
            if(type.equals(1)){
                example.createCriteria().andUsernameEqualTo(name);
            }else if(type.equals(2)){
                example.createCriteria().andPhoneEqualTo(name);
            }else {
                example.createCriteria().andEmailEqualTo(name);
            }
            List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
            if(tbUsers!=null&&tbUsers.size()>0){
                is.setStatus(200);
                is.setData(false);
            }else{
                is.setData(true);
                is.setStatus(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }

    //注册用户
    @Override
    public int register(TbUser tbUser) {
        try {
            return tbUserMapper.insertSelective(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 用户登录
     * @param tbUser
     * @return
     */
    @Override
    public TbUser login(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(tbUser.getUsername());
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if(tbUsers!=null&&tbUsers.size()==1){
            return tbUsers.get(0);
        }
        return null;
    }
}
