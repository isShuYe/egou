package com.ego.provider.service;

import com.ego.pojo.TbItemParamItem;

public interface ParamItemService {
    //查询商品的规格
    TbItemParamItem findParamItem(long cid);
}
