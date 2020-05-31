package com.ego.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.TreeNode;
import com.ego.manager.service.ManagerItemCatService;
import com.ego.pojo.TbItemCat;
import com.ego.provider.service.ItemCatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {
    @Reference
    private ItemCatService itemCatService;

    @Override
    public List<TreeNode> getItemCatList(long id) {
        List<TbItemCat> itemCatList = itemCatService.getItemCatList(id);
        List<TreeNode> treeList = new ArrayList<>();
        for (TbItemCat tic:itemCatList){
            TreeNode tn = new TreeNode();
            tn.setId(tic.getId());
            tn.setText(tic.getName());
            tn.setState(tic.getIsParent()?"closed":"open");
            treeList.add(tn);
        }
        return treeList;
    }
}
