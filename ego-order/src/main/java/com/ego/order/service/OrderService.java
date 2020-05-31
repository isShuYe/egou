package com.ego.order.service;

import carItem.CarItem;
import com.ego.entity.OrderItem;
import com.ego.entity.Result;
import com.ego.pojo.TbUser;

import java.util.List;

public interface OrderService {

    public List<CarItem> carOrder(TbUser user,long[] id);

    public Result insOrderItem(TbUser user, OrderItem orderItem);
}
