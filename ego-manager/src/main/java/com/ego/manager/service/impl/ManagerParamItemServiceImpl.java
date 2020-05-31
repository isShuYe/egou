package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.ItemStatus;
import com.ego.manager.service.ManagerParamItemService;
import com.ego.pojo.TbItemParamItem;
import com.ego.provider.service.ParamItemService;
import org.springframework.stereotype.Service;

@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {
    @Reference
    private ParamItemService paramItemService;

    @Override
    public ItemStatus getParamItem(long cid) {
        ItemStatus is = new ItemStatus();
        try {
            TbItemParamItem paramItem = paramItemService.findParamItem(cid);
            is.setStatus(200);
            is.setData(paramItem);
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }
}
