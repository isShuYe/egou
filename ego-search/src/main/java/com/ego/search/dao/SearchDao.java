package com.ego.search.dao;

import com.ego.pojo.TbItem;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public interface SearchDao {
    QueryResponse getSolr(SolrQuery solrQuery);

    //数据初始化到索引库
    int insert(List<TbItem> tbItems);

    //从索引库删除数据
    void delete();
}
