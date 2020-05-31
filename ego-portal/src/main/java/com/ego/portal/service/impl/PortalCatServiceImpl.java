package com.ego.portal.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.CatNode;
import com.ego.commons.CatResult;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbItemCat;
import com.ego.portal.service.PortalCatService;
import com.ego.provider.service.ItemCatService;
import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalCatServiceImpl implements PortalCatService {
    @Reference
    private ItemCatService itemCatService;
    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Override
    public String show() {
        String catlist = null;
        if (jedisClusterDao.exists("catlist")){
            catlist = jedisClusterDao.get("catlist");
        }else{
            List<TbItemCat> all = itemCatService.findAll();
            List<Object> list = diGui(0, all);
            CatResult catResult = new CatResult();
            catResult.setData(list);
            catlist = JsonUtils.objectToJson(catResult);
            jedisClusterDao.set("catlist",catlist);
        }
        return catlist;
    }

    //使用递归获取数据
    private List<Object> diGui(long pid,List<TbItemCat> all){
        List<Object> list = new ArrayList<>();
        for (TbItemCat tc:all){
            if(tc.getParentId()==pid){
                if(tc.getIsParent()){
                    CatNode catNode = new CatNode();
                    catNode.setUrl("/products/"+tc.getId()+".html");
                    catNode.setName("<a href='/products/"+tc.getId()+".html'>"+tc.getName()+"</a>");
                    catNode.setList(diGui(tc.getId(),all));
                    list.add(catNode);
                }else{
                    list.add("/products/"+tc.getId()+".html|"+tc.getName());
                }
            }
        }
        return list;
    }
}
