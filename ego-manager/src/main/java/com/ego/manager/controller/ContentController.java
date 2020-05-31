package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerContentService;
import com.ego.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ManagerContentService managerContentService;

    /**
     * 添加内容
     * @param categoryId
     * @param tbContent
     * @return
     */
    @RequestMapping("content/save")
    @ResponseBody
    public ItemStatus save(long categoryId, TbContent tbContent){
        return managerContentService.add(categoryId,tbContent);
    }

    /**
     * 查询内容并分页显示
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("content/query/list")
    @ResponseBody
    public PageResult list(long categoryId, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "20") Integer rows){
        return managerContentService.sel(categoryId,page,rows);
    }

    /**
     * 删除内容
     * @param ids
     * @return
     */
    @RequestMapping("content/delete")
    @ResponseBody
    public ItemStatus delete(String ids){
        return managerContentService.delete(ids);
    }


    @RequestMapping("rest/content/edit")
    @ResponseBody
    public ItemStatus update(TbContent tbContent){
        return managerContentService.update(tbContent);
    }
}
