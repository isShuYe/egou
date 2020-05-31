package com.ego.provider.service;

import com.ego.commons.TreeNode;
import com.ego.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    //查询商品类目
    List<TbItemCat> getItemCatList(long id);

    List<TbItemCat> findAll();


}
