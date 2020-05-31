package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.manager.service.ManagerParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamItemController {
    @Autowired
    private ManagerParamItemService managerParamItemService;

    //编辑商品时回显规格参数
    @RequestMapping("param/item/query/{cid}")
    @ResponseBody
    public ItemStatus query(@PathVariable long cid){
        return managerParamItemService.getParamItem(cid);
    }
}
