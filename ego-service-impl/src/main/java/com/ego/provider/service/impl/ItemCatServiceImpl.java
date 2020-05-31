package com.ego.provider.service.impl;

import com.ego.commons.TreeNode;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import com.ego.provider.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> getItemCatList(long id) {

        TbItemCatExample ic = new TbItemCatExample();
        //把id作为ParentId字段的值
        ic.createCriteria().andParentIdEqualTo(id);

        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(ic);
        System.out.println(tbItemCats);
        return tbItemCats;
    }

    @Override
    public List<TbItemCat> findAll() {
        TbItemCatExample example = new TbItemCatExample();
        return tbItemCatMapper.selectByExample(example);
    }

}
