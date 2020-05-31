package com.ego.provider.service;

import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {
    //查询内容分类树
    List<TbContentCategory> findCategory(long cid);

    //新增内容节点
    int insertCategory(TbContentCategory tbContentCategory);

    //修改内容节点
    int updateCategory(TbContentCategory TbContentCategory);

    //删除内容节点
    int deleteCategory(TbContentCategory tbContentCategory);
}
