package com.ego.manager.service;

import com.ego.commons.PageResult;
import com.ego.pojo.TbItem;

public interface ManagerItemService {
    //动态查询商品并分页显示
    PageResult<TbItem> find(Integer page,Integer rows);

    //修改商品删除，上架，下架的状态
    int updateItemStatus(String ids,byte status);

    //新增商品
    int insertItem(TbItem tbItem,String desc,String paramData) throws Exception;

    //修改商品
    int updateItem(TbItem tbItem,String desc,String itemParams) throws Exception;
}
