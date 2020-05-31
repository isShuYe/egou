package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.manager.service.ManagerItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemDescController {

    @Autowired
    private ManagerItemDescService managerItemDescService;

    @RequestMapping("item/desc/{id}")
    @ResponseBody
    public ItemStatus findDesc(@PathVariable long id){
        return managerItemDescService.getItemDesc(id);
    }
}
