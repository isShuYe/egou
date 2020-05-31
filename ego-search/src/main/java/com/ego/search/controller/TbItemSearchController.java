package com.ego.search.controller;

import com.ego.pojo.TbItem;
import com.ego.search.service.TbItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbItemSearchController {
    @Autowired
    private TbItemSearchService tbItemSearchService;

    /**
     * 查询商品的基本信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("item/{id}")
    public String selItem(@PathVariable long id, Model model){
        TbItem tbItem = tbItemSearchService.findById(id);
        model.addAttribute("item",tbItem);
        return "/item";
    }

    /**
     * 查询商品的描述内容
     */
    @RequestMapping(value="item/desc/{id}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String desc(@PathVariable long id){
        return tbItemSearchService.itemDesc(id);
    }


    @RequestMapping(value = "item/param/{id}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String param(@PathVariable long id){

        return tbItemSearchService.itemParamItem(id);
    }
}
