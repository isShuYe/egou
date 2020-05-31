package com.ego.provider.service.impl;

import com.ego.commons.PageResult;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.ego.provider.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public int insertContent(TbContent tbContent) {
        return tbContentMapper.insertSelective(tbContent);
    }

    @Override
    public PageResult findContent(long cid, Integer page, Integer rows) {
        //定义分页规则
        Page<Object> p = PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(example);
        PageResult pageResult = new PageResult();
        pageResult.setRows(tbContents);
        pageResult.setTotal(p.getTotal());
        return pageResult;
    }

    @Override
    public int deleteContent(long id) {
        return tbContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateContent(TbContent tbContent) {
        return tbContentMapper.updateByPrimaryKeyWithBLOBs(tbContent);
    }

    @Override
    public List<TbContent> bigPicListService(long cid) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(cid);
        example.setOrderByClause("updated desc");
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(example);
        return tbContents;
    }
}
