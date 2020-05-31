package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerItemParamService;
import com.ego.pojo.TbItemParam;
import com.ego.provider.service.ItemParamService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {
    @Reference
    private ItemParamService itemParamService;

    @Override
    public PageResult<TbItemParam> findTbItemParam(Integer page, Integer rows) {
        return itemParamService.findAll(page,rows);
    }

    @Override
    public ItemStatus getItemParamByCid(long cid) {
        TbItemParam itemParamByCid = itemParamService.findItemParamByCid(cid);
        ItemStatus is = new ItemStatus();
        try {
            is.setStatus(200);
            is.setData(itemParamByCid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }

    @Override
    public ItemStatus addParamData(long cid, String paramData) {
        ItemStatus is = new ItemStatus();

        Date date = new Date();

        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        int i = itemParamService.insertParamDate(tbItemParam);
        if(i==1){
            is.setStatus(200);
        }
        return is;
    }

    @Override
    public ItemStatus delParamData(String ids) {
        int i =0;
        String[] split = ids.split(",");
        for(String idStr:split){
            long id = Long.parseLong(idStr);
            i+=itemParamService.deleteParamDate(id);
        }
        ItemStatus is = new ItemStatus();
        if (i==split.length){
            is.setStatus(200);
        }
        return is;
    }

    @Override
    public ItemStatus updParamData(long id, String paramData) {
        TbItemParam tbItemParam =  new TbItemParam();
        tbItemParam.setItemCatId(id);
        tbItemParam.setParamData(paramData);
        tbItemParam.setUpdated(new Date());

        ItemStatus is = new ItemStatus();
        int n = itemParamService.updateParamData(tbItemParam);
        if(n==1){
            is.setStatus(200);
        }
        return is;
    }
}
