package com.ego.provider.service;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.pojo.TbItemParam;

public interface ItemParamService {
    //查询商品规格参数并分页显示
    PageResult<TbItemParam> findAll(Integer page, Integer rows);

    //查询类目是否有规格参数
    TbItemParam findItemParamByCid(long catid);

    //新增类目的规格参数
    int insertParamDate(TbItemParam tbItemParam);

    //删除类目的规格参数
    int deleteParamDate(long id);

    //修改规格参数
    int updateParamData(TbItemParam tbItemParam);
}
