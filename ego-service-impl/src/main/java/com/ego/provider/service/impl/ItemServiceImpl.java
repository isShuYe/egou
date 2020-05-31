package com.ego.provider.service.impl;

import com.ego.commons.PageResult;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.*;
import com.ego.provider.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override//动态查询商品并分页显示
    public PageResult<TbItem> findAll(Integer page, Integer rows) {
        Page<Object> p = PageHelper.startPage(page, rows);

        TbItemExample example = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);

        PageResult<TbItem> pr = new PageResult<>();
        pr.setTotal(p.getTotal());
        pr.setRows(tbItems);
        return pr;
    }

    //修改商品删除，上架，下架的状态
    @Override
    public int updateItemStatus(TbItem tbItem) {
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    //新增商品
    @Override
    public int insertItem(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws Exception {
        int n = 0;
        try {
            n = tbItemMapper.insert(tbItem);
            n += tbItemDescMapper.insert(tbItemDesc);
            n+=tbItemParamItemMapper.insert(tbItemParamItem);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(n==3){
            return 1;
        }else{
            throw new Exception("新增失败");
        }

    }

    @Override
    public int updateItem(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws Exception {
        int n = 0;
        try {
            n = tbItemMapper.updateByPrimaryKeySelective(tbItem);

            TbItemDescExample example = new TbItemDescExample();
            example.createCriteria().andItemIdEqualTo(tbItem.getId());
            int i = tbItemDescMapper.countByExample(example);
            if (i==0){
                n+=tbItemDescMapper.insert(tbItemDesc);
            }else{
                tbItemDesc.setCreated(null);
                n+=tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
            }

            TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
            tbItemParamItemExample.createCriteria().andItemIdEqualTo(tbItem.getId());
            n+=tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem,tbItemParamItemExample);
            System.out.println(n);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(n==3){
            return 1;
        }else{
            throw new Exception("修改失败");
        }
    }

    @Override
    public List<TbItem> itemList(byte status) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andStatusEqualTo(status);
        return tbItemMapper.selectByExample(example);
    }

    @Override
    public TbItem findById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}