package com.ego.manager.service;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.pojo.TbContent;

public interface ManagerContentService {
    ItemStatus add(long cid, TbContent tbContent);

    PageResult sel(long cid,Integer page,Integer rows);

    ItemStatus delete(String ids);

    ItemStatus update(TbContent tbContent);
}
