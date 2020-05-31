package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.IDUtils;
import com.ego.commons.ItemStatus;
import com.ego.commons.TreeNode;
import com.ego.manager.service.ManagerContentCategoryService;
import com.ego.pojo.TbContentCategory;
import com.ego.provider.service.ContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {
    @Reference
    private ContentCategoryService contentCategoryService;

    @Override//查询内容分类树
    public List<TreeNode> getContentCategory(long cid) {
        List<TreeNode> list = new ArrayList<>();
        List<TbContentCategory> category = contentCategoryService.findCategory(cid);
        for (TbContentCategory tc:category){
            TreeNode tn = new TreeNode();
            tn.setText(tc.getName());
            tn.setId(tc.getId());
            tn.setState(tc.getIsParent()?"closed":"open");
            list.add(tn);
        }
        return list;
    }

    @Override//新增内容节点
    public ItemStatus addContentCategory(TbContentCategory tbContentCategory) {
        ItemStatus is = new ItemStatus();
        long id = IDUtils.genItemId();
        tbContentCategory.setId(id);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        Date date = new Date();
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);

        int i = contentCategoryService.insertCategory(tbContentCategory);
        if (i>0){
            //设置父节点
            TbContentCategory tbParent = new TbContentCategory();
            tbParent.setId(tbContentCategory.getParentId());
            tbParent.setIsParent(true);
            contentCategoryService.updateCategory(tbParent);
        }
        is.setStatus(200);
        is.setData(tbContentCategory);
        return is;
    }

    @Override
    public ItemStatus updContentCategory(TbContentCategory tbContentCategory) {
        ItemStatus is = new ItemStatus();
        try {
            int n = contentCategoryService.updateCategory(tbContentCategory);
            if(n>0){
                is.setStatus(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }

    @Override
    public ItemStatus delContentCategory(TbContentCategory tbContentCategory) {
        int i = contentCategoryService.deleteCategory(tbContentCategory);
        ItemStatus is = new ItemStatus();
        if (i>0){
            TbContentCategory tcc = new TbContentCategory();
            tcc.setId(tbContentCategory.getParentId());
            tcc.setIsParent(false);
            tcc.setUpdated(new Date());
            contentCategoryService.updateCategory(tcc);
        }
        is.setStatus(200);
        return is;
    }

}
