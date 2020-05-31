package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.commons.TreeNode;
import com.ego.manager.service.ManagerContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ManagerContentCategoryService managerContentCategoryService;

    /**
     *查询内容分类树
     * @param id
     * @return
     */
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<TreeNode> list(@RequestParam(defaultValue = "0") long id){
        return managerContentCategoryService.getContentCategory(id);
    }

    /**
     * 新增节点
     * @return
     */
    @RequestMapping("content/category/create")
    @ResponseBody
    public ItemStatus create(TbContentCategory tbContentCategory){
        return managerContentCategoryService.addContentCategory(tbContentCategory);
    }

    /**
     * 修改节点
     * @return
     */
    @RequestMapping("content/category/update")
    @ResponseBody
    public ItemStatus update(TbContentCategory tbContentCategory){
        return managerContentCategoryService.updContentCategory(tbContentCategory);
    }

    //删除节点
    @RequestMapping("content/category/delete/")
    @ResponseBody
    public ItemStatus delete(TbContentCategory tbContentCategory){
        return managerContentCategoryService.delContentCategory(tbContentCategory);
    }
}
