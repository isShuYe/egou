package com.ego.provider.service.impl;

import com.ego.mapper.TbOrderItemMapper;
import com.ego.mapper.TbOrderMapper;
import com.ego.mapper.TbOrderShippingMapper;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.provider.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderItemService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public int addOrder(TbOrder order, List<TbOrderItem> tbOrderItems, TbOrderShipping tbOrderShipping) throws Exception{
        int index = 0;
        //订单表
        index+=tbOrderMapper.insertSelective(order);
        //订单明细表
        for(TbOrderItem tbOrderItem:tbOrderItems){
            index+=tbOrderItemMapper.insertSelective(tbOrderItem);
        }
        //订单物流表
        index+=tbOrderShippingMapper.insertSelective(tbOrderShipping);
        if (index==2+tbOrderItems.size()){
            return 1;
        }else {
            throw new Exception();
        }
    }
}
