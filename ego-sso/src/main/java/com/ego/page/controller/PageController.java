package com.ego.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page, @RequestHeader("Referer") String url, Model model){
        model.addAttribute("redirect",url);
        return page;
    }
}
