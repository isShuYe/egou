package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.ItemStatus;
import com.ego.manager.service.ManagerItemDescService;
import com.ego.pojo.TbItemDesc;
import com.ego.provider.service.ItemDescService;
import org.springframework.stereotype.Service;

@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {
    @Reference
    private ItemDescService itemDescService;

    @Override
    public ItemStatus getItemDesc(long id) {
        TbItemDesc itemDesc = itemDescService.getItemDesc(id);
        ItemStatus is = new ItemStatus();
        if(itemDesc!=null){
            is.setStatus(200);
            is.setData(itemDesc);
            return is;
        }else{
            return is;
        }
    }
}
