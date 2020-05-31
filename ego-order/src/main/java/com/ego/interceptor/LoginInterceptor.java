package com.ego.interceptor;

import com.ego.commons.CookieUtils;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbUser;
import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tt_token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if(!StringUtils.isEmpty(tt_token)){
            String s = jedisClusterDao.get(tt_token);
            TbUser tbUser = JsonUtils.jsonToPojo(s, TbUser.class);
            request.setAttribute("user",tbUser);
            if(tbUser!=null){
                return true;
            }
        }
        String url = request.getRequestURL().toString();
        response.sendRedirect("http://localhost:8083/login?redirect="+url);
        return false;
    }
}
