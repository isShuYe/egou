package com.ego.manager.service;

import com.ego.commons.ItemStatus;
import com.ego.commons.TreeNode;
import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface ManagerContentCategoryService {
    List<TreeNode> getContentCategory(long cid);

    ItemStatus addContentCategory(TbContentCategory tbContentCategory);

    ItemStatus updContentCategory(TbContentCategory tbContentCategory);

    ItemStatus delContentCategory(TbContentCategory tbContentCategory);
}
