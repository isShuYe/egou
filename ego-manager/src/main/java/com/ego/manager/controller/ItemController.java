package com.ego.manager.controller;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerItemService;
import com.ego.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ManagerItemService managerItemService;

    //查询商品并分页显示
    @RequestMapping("item/list")
    @ResponseBody
    public PageResult<TbItem> findAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows){
        return managerItemService.find(page, rows);
    }

    //修改商品删除状态
    @RequestMapping("rest/item/delete")
    @ResponseBody
    public ItemStatus deleteStatus(String ids){
        int i = managerItemService.updateItemStatus(ids, (byte) 3);
        ItemStatus is = new ItemStatus();
        if(i>0){
            is.setStatus(200);
        }
        return is;
    }
    //修改商品上架状态
    @RequestMapping("rest/item/reshelf")
    @ResponseBody
    public ItemStatus reshelfStatus(String ids){
        int i = managerItemService.updateItemStatus(ids, (byte) 1);
        ItemStatus is = new ItemStatus();
        if(i>0){
            is.setStatus(200);
        }
        return is;
    }
    //修改商品下架状态
    @RequestMapping("rest/item/instock")
    @ResponseBody
    public ItemStatus instockStatus(String ids){
        int i = managerItemService.updateItemStatus(ids, (byte) 2);
        ItemStatus is = new ItemStatus();
        if(i>0){
            is.setStatus(200);
        }
        return is;
    }

    //编辑商品
    @RequestMapping("rest/page/item-edit")
    public String itemEdit(){
        return "item-edit";
    }
    //修改商品
    @RequestMapping("rest/item/update")
    @ResponseBody
    public ItemStatus update(TbItem tbItem,String desc,String itemParams){
        ItemStatus is = new ItemStatus();
        try{
            int n = managerItemService.updateItem(tbItem,desc,itemParams);
            if (n==1){
                is.setStatus(200);
            }
        }catch (Exception e){
            e.printStackTrace();
            is.setData(e.getMessage());
        }
        return is;
    }

    //新增商品
    @RequestMapping("item/save")
    @ResponseBody
    public ItemStatus save(TbItem tbItem,String desc,String itemParams){
        ItemStatus is = new ItemStatus();
        try{
            int n = managerItemService.insertItem(tbItem,desc,itemParams);
            if (n==1){
                is.setStatus(200);
            }
        }catch (Exception e){
            e.printStackTrace();
            is.setData(e.getMessage());
        }
        return is;
    }
}
