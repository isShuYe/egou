package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerItemParamService;
import com.ego.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ManagerItemParamService managerItemParamService;

    //查询所有商品的规格参数
    @RequestMapping("item/param/list")
    @ResponseBody
    public PageResult<TbItemParam> findTbItemParam(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows){
        return managerItemParamService.findTbItemParam(page,rows);
    }

    //查询商品类目是否添加了规格参数
    @RequestMapping("item/param/query/{cid}")
    @ResponseBody
    public ItemStatus queryByCid(@PathVariable long cid){
        return managerItemParamService.getItemParamByCid(cid);
    }

    //添加商品类目的规格参数
    @RequestMapping("item/param/save/{cid}")
    @ResponseBody
    public ItemStatus save(@PathVariable long cid,String paramData){
        return managerItemParamService.addParamData(cid,paramData);
    }

    //删除类目的规格参数
    @RequestMapping("item/param/delete")
    @ResponseBody
    public ItemStatus delete(String ids){
        return managerItemParamService.delParamData(ids);
    }

    //添加商品时回显类目规格
    @RequestMapping("item/param/query/itemcatid/{cid}")
    @ResponseBody
    public ItemStatus itemQueryByCid(@PathVariable long cid){
        return managerItemParamService.getItemParamByCid(cid);
    }

    //修改规格参数
    @RequestMapping("item/param/update/{cid}")
    @ResponseBody
    public ItemStatus update(@PathVariable long cid,String paramData){
        return managerItemParamService.updParamData(cid,paramData);
    }
}
