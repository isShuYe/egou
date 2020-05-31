package com.ego.provider.service;

import com.ego.commons.ItemStatus;
import com.ego.pojo.TbUser;

public interface RegisterService {
    //验证用户名唯一性
    ItemStatus yanZheng(String name,Integer type);

    //注册用户
    int register(TbUser tbUser);

    //登录用户
    TbUser login(TbUser tbUser);
}
