package com.ego.portal.controller;

import com.ego.portal.service.PortalContentServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PortalContentController {
    @Autowired
    private PortalContentServcice portalContentServcice;

    /**
     * 显示大广告数据
     * @param categoryId
     * @return
     */
    @RequestMapping("content/bigpic/list")
    @ResponseBody
    public String bigPicList(long categoryId){
        return portalContentServcice.getBigPicListService(categoryId);
    }
}
