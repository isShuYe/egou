package com.ego.order.controller;

import carItem.CarItem;
import com.ego.entity.OrderItem;
import com.ego.entity.Result;
import com.ego.order.service.OrderService;
import com.ego.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 显示购物车商品库存
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("order/order-cart")
    public String ordercart(@RequestParam("id") long[] id, HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        List<CarItem> carItems = orderService.carOrder(user, id);
        request.setAttribute("orderList",carItems);
        return "ordercart";
    }

    @RequestMapping("order/success")
    public String success(HttpServletRequest request, OrderItem orderItem){
        TbUser user = (TbUser) request.getAttribute("user");
        Result result = orderService.insOrderItem(user, orderItem);
        if(result!=null){
            request.setAttribute("result",result);
        }
        return "success";
    }
}
