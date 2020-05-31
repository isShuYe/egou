package com.ego.sso.controller;

import com.ego.commons.ItemStatus;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbUser;
import com.ego.sso.service.SsoRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {
    @Autowired
    private SsoRegisterService ssoRegisterService;

    //验证用户名唯一性
    @RequestMapping("user/check/{username}/{type}")
    @ResponseBody
    public ItemStatus check(@PathVariable String username,@PathVariable Integer type){
        return ssoRegisterService.userYanZhang(username, type);
    }

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @RequestMapping("user/register")
    @ResponseBody
    public ItemStatus register(TbUser tbUser){
        return ssoRegisterService.register(tbUser);
    }

    /**
     * 用户登录
     * @param tbUser
     * @param model
     * @return
     */
    @RequestMapping(value = "user/login")
    @ResponseBody
    public ItemStatus login(TbUser tbUser, Model model, HttpServletRequest req, HttpServletResponse resp){
        return ssoRegisterService.login(tbUser,req,resp);
    }

    /**
     * 用户的登录状态
     * @param token
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public Object token(@PathVariable String token,String callback,HttpServletRequest req){
        ItemStatus is = ssoRegisterService.loginMsg(token,req);
        if(callback!=null&&!callback.equals("")){
            String s = JsonUtils.objectToJson(is);
            return callback+"("+s+")";
        }
        return is;
    }

    //用户退出
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token,String callback){
        ItemStatus logout = ssoRegisterService.logout(token);
        if(StringUtils.isEmpty(callback)){
            String s = JsonUtils.objectToJson(logout);
            return callback+"("+s+")";
        }
        return logout;
    }
}
