package com.ego.provider.service.impl;

import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;
import com.ego.provider.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ParamItemServiceImpl implements ParamItemService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem findParamItem(long cid) {
        System.out.println(cid);
        try {
            TbItemParamItemExample example = new TbItemParamItemExample();
            example.createCriteria().andItemIdEqualTo(cid);
            List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
            if(tbItemParamItems!=null&&tbItemParamItems.size()==1){
                return tbItemParamItems.get(0);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
