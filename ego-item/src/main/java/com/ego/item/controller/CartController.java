package com.ego.item.controller;

import com.ego.commons.ItemStatus;
import com.ego.item.service.CarItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CarItemService carItemService;

    /**
     * 添加商品到购物车
     * @param itemid
     * @param request
     * @return
     */
    @RequestMapping("cart/add/{itemid}")
    public String cartAdd(@PathVariable long itemid, HttpServletRequest request){
        TbUser user = (TbUser)request.getAttribute("user");
        carItemService.addCarItem(user, itemid);
        return "cartSuccess";
    }

    /**
     * 动态显示购物车
     * @param request
     * @return
     */
    @RequestMapping("cart/cart")
    public String show(HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> tbItems = carItemService.findCarItem(user);
        request.setAttribute("cartList",tbItems);
        return "cart";
    }

    /**
     * 商品数量的增减
     * @param itemid
     * @param num
     * @param request
     * @return
     */
    @RequestMapping("cart/update/num/{itemid}/{num}")
    public String update(@PathVariable long itemid,@PathVariable Integer num,HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        carItemService.updateNum(user,itemid,num);
        return "cart";
    }

    @RequestMapping("cart/delete/{itemid}")
    @ResponseBody
    public ItemStatus delete(@PathVariable long itemid, HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        return carItemService.deleteItem(user, itemid);
    }
}
