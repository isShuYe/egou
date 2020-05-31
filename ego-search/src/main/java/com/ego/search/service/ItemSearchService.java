package com.ego.search.service;

import com.ego.entity.SearchResult;

public interface ItemSearchService {
    int init();

    void delete();

    public SearchResult selSearchService(String query,Integer page,Integer rows);

}
