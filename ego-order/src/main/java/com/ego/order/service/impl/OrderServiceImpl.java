package com.ego.order.service.impl;

import carItem.CarItem;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.IDUtils;
import com.ego.commons.JsonUtils;
import com.ego.entity.OrderItem;
import com.ego.entity.Result;
import com.ego.order.service.OrderService;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.pojo.TbUser;
import com.ego.provider.service.OrderItemService;
import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JedisClusterDao jedisClusterDao;
    @Reference
    private OrderItemService orderItemService;

    /**
     * 显示购物车商品库存
     * @param user
     * @param id
     * @return
     */
    @Override
    public List<CarItem> carOrder(TbUser user, long[] id) {
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();
        String userJson = jedisClusterDao.get(uid + ":" + username);

        List<CarItem> carItems = JsonUtils.jsonToList(userJson, CarItem.class);
        //创建新集合接收商品id相同的购物车
        List<CarItem> carList = new ArrayList<>();
        if(carItems!=null&&carItems.size()>0){
            for(CarItem car:carItems){
                for(long itemid:id){
                    if (car.getTbItem().getId()==itemid){
                        carList.add(car);
                    }
                }
            }
        }
        return carList;
    }

    /**
     * 提交订单信息保存到数据库
     * @param user
     * @param orderItem
     * @return
     */
    @Override
    public Result insOrderItem(TbUser user,OrderItem orderItem) {
        String uid = String.valueOf(user.getId());
        String username = user.getUsername();

        //订单id
        String oid = IDUtils.genItemId()+"";
        //时间
        Date date = new Date();

        //订单表
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(oid);
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        tbOrder.setPayment(orderItem.getPayment());
        tbOrder.setPaymentType(orderItem.getPaymentType());
        tbOrder.setUserId(user.getId());
        tbOrder.setBuyerNick(username);

        //创建返回给前台的数据
        Result result = new Result();
        result.setOrderId(oid);
        long totalFee = 0;
        //订单明细表
        List<TbOrderItem> tbOrderItemList = new ArrayList<>();
        List<TbOrderItem> tbOrderItems = orderItem.getOrderItems();
        for (TbOrderItem toi:tbOrderItems){
            toi.setId(IDUtils.genItemId()+"");
            toi.setOrderId(oid);
            tbOrderItemList.add(toi);
            totalFee += toi.getTotalFee();
            result.setTotal(totalFee);
        }
        //订单物流表
        TbOrderShipping tbOrderShipping = orderItem.getOrderShipping();
        tbOrderShipping.setCreated(date);
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setOrderId(oid);
        tbOrderShipping.setReceiverPhone(user.getPhone());

        try {
            int i = orderItemService.addOrder(tbOrder,tbOrderItemList,tbOrderShipping);
            if(i>0){
                String carItem = jedisClusterDao.get(uid + ":" + username);
                List<CarItem> carItems = JsonUtils.jsonToList(carItem, CarItem.class);
                List<CarItem> carItemList = new ArrayList<>();
                for (TbOrderItem toi:tbOrderItems){
                    for(CarItem car :carItems){
                        if (toi.getItemId().equals(car.getTbItem().getId()+"")){
                            carItemList.add(car);
                        }
                    }
                }
                for (CarItem carItem1:carItemList){
                    carItems.remove(carItem1);
                }
                String s = JsonUtils.objectToJson(carItems);
                jedisClusterDao.set(uid+":"+username,s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
