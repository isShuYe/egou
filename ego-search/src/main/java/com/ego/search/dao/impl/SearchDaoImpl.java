package com.ego.search.dao.impl;

import com.ego.pojo.TbItem;
import com.ego.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {
    @Autowired
    private CloudSolrServer cloudSolrServer;

    /**
     * 根据关键字检索索引库的数据并分页显示
     * @param solrQuery
     * @return
     */
    @Override
    public QueryResponse getSolr(SolrQuery solrQuery) {
        QueryResponse queryResponse = new QueryResponse();
        try {
            queryResponse = cloudSolrServer.query(solrQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return queryResponse;
    }

    /**
     * 数据初始化
     * @param tbItems
     * @return
     */
    @Override
    public int insert(List<TbItem> tbItems) {
        try {
            List<SolrInputDocument> list = new ArrayList<>();
            for (TbItem tbItem:tbItems){
                SolrInputDocument sid = new SolrInputDocument();
                sid.addField("id",tbItem.getId()+"");
                sid.addField("item_title",tbItem.getTitle());
                sid.addField("item_sell_point",tbItem.getSellPoint());
                sid.addField("item_price",tbItem.getPrice());
                sid.addField("item_image",tbItem.getImage());
                list.add(sid);
            }
            cloudSolrServer.add(list);
            cloudSolrServer.commit();
            if(list!=null&&list.size()>0){
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //删除索引库的数据
    @Override
    public void delete() {
        try {
            cloudSolrServer.deleteByQuery("*:*");
            cloudSolrServer.commit();
            cloudSolrServer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
