package com.ego.provider.service;

import com.ego.commons.PageResult;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

public interface ItemService {
    //查询商品并分页显示
    PageResult<TbItem> findAll(Integer page,Integer rows);

    //修改商品删除，上架，下架的状态
    int updateItemStatus(TbItem tbItem);

    //新增商品
    int insertItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws Exception;
    //修改商品
    int updateItem(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws Exception;

    //查询所有商品
    List<TbItem> itemList(byte status);

    //查询商品详情
    TbItem findById(long id);
}
