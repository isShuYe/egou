package com.ego.provider.service;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderItemService {
    int addOrder(TbOrder order, List<TbOrderItem> tbOrderItems, TbOrderShipping tbOrderShipping) throws Exception;
}
