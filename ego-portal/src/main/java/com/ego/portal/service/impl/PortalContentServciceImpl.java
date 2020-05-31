package com.ego.portal.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.BigPicResult;
import com.ego.commons.JsonUtils;
import com.ego.pojo.TbContent;
import com.ego.portal.service.PortalContentServcice;
import com.ego.provider.service.ContentService;
import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalContentServciceImpl implements PortalContentServcice {
    @Reference
    private ContentService contentService;
    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Override
    public String getBigPicListService(long cid) {
        String key = null;
        if (jedisClusterDao.exists("bigPic")){
            key = jedisClusterDao.get("bigPic");
        }else{
            List<TbContent> tbContents = contentService.bigPicListService(cid);
            List<BigPicResult> list = new ArrayList<>();
            for(int i=0;i<tbContents.size();i++){
                TbContent tbContent = tbContents.get(i);
                if(i<=5){
                    BigPicResult bigPicResult = new BigPicResult();
                    bigPicResult.setWidth(670);
                    bigPicResult.setWidthB(550);
                    bigPicResult.setHeight(240);
                    bigPicResult.setHeightB(240);
                    bigPicResult.setAlt("");
                    bigPicResult.setHref(tbContent.getUrl());
                    bigPicResult.setSrc(tbContent.getPic());
                    bigPicResult.setSrcB(tbContent.getPic2());
                    list.add(bigPicResult);
                    key = JsonUtils.objectToJson(list);
                    jedisClusterDao.set("bigPic",key);
                }
            }
        }
        return key;
    }
}
