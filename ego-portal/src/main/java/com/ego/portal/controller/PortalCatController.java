package com.ego.portal.controller;

import com.ego.portal.service.PortalCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PortalCatController {
    @Autowired
    private PortalCatService portalCatService;

    @RequestMapping(value = "item/cat")
    @ResponseBody
    public String shoCat(){
        return portalCatService.show();
    }
}
