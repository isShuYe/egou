package com.ego.item.service;

import com.ego.commons.ItemStatus;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbUser;

import java.util.List;

public interface CarItemService {
    //将购物车放入redis
    public void addCarItem(TbUser user, long itemid);

    //显示购物车的数据
    public List<TbItem> findCarItem(TbUser user);

    //修改商品的数量
    public void updateNum(TbUser user,long itemid,Integer num);

    //删除商品
    public ItemStatus deleteItem(TbUser user, long itemid);
}
