package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.IDUtils;
import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.ego.provider.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ManagerItemServiceImpl implements ManagerItemService {
    @Reference
    private ItemService itemService;

    @Override
    public PageResult<TbItem> find(Integer page,Integer rows) {
        PageResult<TbItem> findAll = itemService.findAll(page, rows);
        return findAll;
    }

    @Override
    public int updateItemStatus(String ids, byte status) {
        String[] idStr = ids.split(",");
        TbItem ti = new TbItem();
        int i = 0;
        for (String id:idStr){
            ti.setId(Long.parseLong(id));
            ti.setStatus(status);
            i+=itemService.updateItemStatus(ti);
        }
        if(i==idStr.length){
            return 1;
        }
        return 0;
    }

    @Override
    public int insertItem(TbItem tbItem, String desc,String paramData) throws Exception {
        long id = IDUtils.genItemId();
        Date date = new Date();

        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        return itemService.insertItem(tbItem, tbItemDesc,tbItemParamItem);
    }

    @Override
    public int updateItem(TbItem tbItem, String desc,String itemParams) throws Exception {
        Date date = new Date();

        tbItem.setUpdated(date);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);

        return itemService.updateItem(tbItem, tbItemDesc,tbItemParamItem);
    }
}
