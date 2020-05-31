package com.ego.search.controller;

import com.ego.entity.SearchResult;
import com.ego.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SolrController {
    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping(value = "solr/init",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String init(){
        long start = System.currentTimeMillis();
        int i = itemSearchService.init();
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000);
        if(i>0){
            return "添加到索引库成功";
        }else {
            return "添加到索引库失败";
        }
    }

    @RequestMapping(value = "solr/delete",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String delete(){
        itemSearchService.delete();
        return "删除成功";
    }

    @RequestMapping("search")
    public String search(String q, @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "20") Integer rows, Model model){
        String keywords = null;
        try {
            keywords = new String(q.getBytes("ISO-8859-1"),"utf-8");
            SearchResult searchResult = itemSearchService.selSearchService(keywords, page, rows);
            model.addAttribute("query",keywords);
            model.addAttribute("itemList",searchResult.getItemList());
            model.addAttribute("maxPage",searchResult.getMaxPage());
            model.addAttribute("page",page);
            return "/search";

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
