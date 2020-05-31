package com.ego.manager.controller;

import com.ego.commons.TreeNode;
import com.ego.manager.service.ManagerItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    @Autowired
    private ManagerItemCatService managerItemCatService;

    @RequestMapping("item/cat/list")
    @ResponseBody
    public List<TreeNode> treeList(@RequestParam(defaultValue = "0") long id){
        return managerItemCatService.getItemCatList(id);
    }
}
