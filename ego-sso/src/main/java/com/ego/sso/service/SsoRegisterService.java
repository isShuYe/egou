package com.ego.sso.service;

import com.ego.commons.ItemStatus;
import com.ego.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SsoRegisterService {
    ItemStatus userYanZhang(String name,Integer type);

    ItemStatus register(TbUser tbUser);

    ItemStatus login(TbUser tbUser, HttpServletRequest req, HttpServletResponse resp);

    ItemStatus loginMsg(String token, HttpServletRequest req);

    ItemStatus logout(String sessionKey);
}
