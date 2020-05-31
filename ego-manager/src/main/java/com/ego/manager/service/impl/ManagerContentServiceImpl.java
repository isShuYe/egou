package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.manager.service.ManagerContentService;
import com.ego.pojo.TbContent;
import com.ego.provider.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ManagerContentServiceImpl implements ManagerContentService {
    @Reference
    private ContentService contentService;

    @Override
    public ItemStatus add(long cid, TbContent tbContent) {
        Date date = new Date();
        tbContent.setCategoryId(cid);
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        int i = contentService.insertContent(tbContent);
        ItemStatus is = new ItemStatus();
        if (i==1){
            is.setStatus(200);
        }
        return is;
    }

    @Override
    public PageResult sel(long cid, Integer page, Integer rows) {
        return contentService.findContent(cid,page,rows);
    }

    @Override
    public ItemStatus delete(String ids) {
        ItemStatus is = new ItemStatus();
        int i = 0;
        try {
            String[] split = ids.split(",");
            for (String idStr:split){
                long id = Long.parseLong(idStr);
                i+=contentService.deleteContent(id);
            }
            if(i==split.length){
                is.setStatus(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }

    //修改内容
    @Override
    public ItemStatus update(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        if (tbContent.getCreated()==null){
            tbContent.setCreated(date);
        }
        ItemStatus is = new ItemStatus();
        try {
            contentService.updateContent(tbContent);
            is.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }
}
