package com.ego.entity;

import com.ego.commons.ItemSolr;

import java.util.List;

public class SearchResult {

    private List<ItemSolr> itemList; //商品集合
    private long maxPage; //最大页
    private long totalPages; //总条数

    public List<ItemSolr> getItemList() {
        return this.itemList;
    }

    public void setItemList(final List<ItemSolr> itemList) {
        this.itemList = itemList;
    }

    public long getMaxPage() {
        return this.maxPage;
    }

    public void setMaxPage(final long maxPage) {
        this.maxPage = maxPage;
    }

    public long getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(final long totalPages) {
        this.totalPages = totalPages;
    }
}
