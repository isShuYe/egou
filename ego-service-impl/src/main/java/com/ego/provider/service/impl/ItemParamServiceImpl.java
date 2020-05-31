package com.ego.provider.service.impl;

import com.ego.commons.PageResult;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.ego.provider.service.ItemParamService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override//查询所有的规格参数并分页显示
    public PageResult<TbItemParam> findAll(Integer page, Integer rows) {
        //设置分页规则
        Page p = PageHelper.startPage(page, rows);

        //查询所有的规格参数
        TbItemParamExample example = new TbItemParamExample();
        //查询列中具备大字段属性text的列
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);

        PageResult<TbItemParam> pageResult = new PageResult<>();
        pageResult.setTotal(p.getTotal());
        pageResult.setRows(tbItemParams);
        return pageResult;
    }

    @Override
    public TbItemParam findItemParamByCid(long catid) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(catid);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(tbItemParams!=null&&tbItemParams.size()==1){
            return tbItemParams.get(0);
        }
        return null;
    }

    @Override
    public int insertParamDate(TbItemParam tbItemParam) {
        int i=0;
        try {
            i=tbItemParamMapper.insert(tbItemParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    //删除类目的规格参数
    @Override
    public int deleteParamDate(long id) {
        int i=0;
        try {
            i= tbItemParamMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateParamData(TbItemParam tbItemParam) {
        TbItemParamExample example = new TbItemParamExample();
        return tbItemParamMapper.updateByExampleSelective(tbItemParam,example);
    }


}
