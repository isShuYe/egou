package com.ego.provider.service.impl;

import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import com.ego.provider.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> findCategory(long cid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(cid);
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insertCategory(TbContentCategory tbContentCategory) {
        int n=0;
        try {
            n+=tbContentCategoryMapper.insertSelective(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int updateCategory(TbContentCategory TbContentCategory) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(TbContentCategory);
    }

    @Override
    public int deleteCategory(TbContentCategory tbContentCategory) {
        int n = 0;
        try {
            n+=tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }


}
