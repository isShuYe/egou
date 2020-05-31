package com.ego.search.service;

import com.ego.pojo.TbItem;

public interface TbItemSearchService {
    //查询商品的基本信息
    TbItem findById(long id);

    //查询商品的描述内容
    String itemDesc(long id);

    //查询商品的规格参数
    String itemParamItem(long cid);
}
