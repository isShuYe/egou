package com.ego.provider.service;

import com.ego.commons.PageResult;
import com.ego.pojo.TbContent;

import java.util.List;

public interface ContentService {
    //新增内容
    int insertContent(TbContent tbContent);

    //查询所有内容并分页
    PageResult findContent(long cid, Integer page, Integer rows);

    //删除内容
    int deleteContent(long id);

    //修改内容
    int updateContent(TbContent tbContent);

    //显示大广告
    List<TbContent> bigPicListService(long cid);
}
