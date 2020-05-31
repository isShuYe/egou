package com.ego.manager.service;

import com.ego.commons.ItemStatus;
import com.ego.commons.PageResult;
import com.ego.pojo.TbItemParam;

public interface ManagerItemParamService {

    //查询所有规格参数并分页显示
    PageResult<TbItemParam> findTbItemParam(Integer page,Integer rows);

    //查询商品类目是否有规格参数
    ItemStatus getItemParamByCid(long cid);

    //新增商品类目的规格参数
    ItemStatus addParamData(long cid,String paramData);

    //删除类目的规格参数
    ItemStatus delParamData(String ids);

    //修改规格参数
    ItemStatus updParamData(long id,String paramData);
}
