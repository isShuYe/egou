package com.ego.search.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.ItemSolr;
import com.ego.entity.SearchResult;
import com.ego.pojo.TbItem;
import com.ego.provider.service.ItemService;
import com.ego.search.dao.SearchDao;
import com.ego.search.service.ItemSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    @Reference
    private ItemService itemService;
    @Autowired
    private SearchDao searchDao;

    /**
     * 数据初始化到索引库
     * @return
     */
    @Override
    public int init() {
        List<TbItem> tbItems = itemService.itemList((byte) 1);
        return searchDao.insert(tbItems);
    }

    //删除索引库中的数据
    @Override
    public void delete() {
        searchDao.delete();
    }

    /**
     * 根据关键字从索引库检索
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @Override
    public SearchResult selSearchService(String query, Integer page, Integer rows) {
        SolrQuery params = new SolrQuery();
        //设置查询条件
        if(query!=null){
            params.setQuery("item_keywords:"+query);
        }else {
            params.set("q","*:*");
        }

        //设置检索的默认域
        params.set("df","item_keywords");
        //设置分页
        params.setStart(rows*(page-1));
        params.setRows(rows);
        //设置高亮
        //开启高亮
        params.setHighlight(true);
        //设置要高亮的内容
        params.addHighlightField("item_title");
        params.setHighlightSimplePre("<font color='red'>");
        params.setHighlightSimplePost("</font>");
        QueryResponse resp = searchDao.getSolr(params);
        SolrDocumentList results = resp.getResults();
        //获取商品的总数
        long numFound = results.getNumFound();
        //设置最大页
        long maxPage = numFound / rows;

        //通过绑定将检索到的商品数据集合转化成接收封装商品的集合
        DocumentObjectBinder dob = new DocumentObjectBinder();
        List<ItemSolr> beans = dob.getBeans(ItemSolr.class, results);

        //取出高亮内容
        Map<String, Map<String, List<String>>> highlighting = resp.getHighlighting();
        for (ItemSolr is:beans){
            String id = is.getId();
            List<String> item_title = highlighting.get(id).get("item_title");
            if(item_title!=null&&item_title.size()>0){
                is.setTitle(item_title.get(0));
            }
        }


        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(beans);
        searchResult.setMaxPage(maxPage+1);
        searchResult.setTotalPages(numFound);
        return searchResult;
    }


}
