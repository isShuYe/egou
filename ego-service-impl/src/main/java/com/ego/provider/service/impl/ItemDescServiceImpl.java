package com.ego.provider.service.impl;

import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import com.ego.provider.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getItemDesc(long id) {
        return tbItemDescMapper.selectByPrimaryKey(id);
    }
}
