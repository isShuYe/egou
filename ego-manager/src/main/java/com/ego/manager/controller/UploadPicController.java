package com.ego.manager.controller;

import com.ego.commons.UploadResult;
import com.ego.manager.service.UploadPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadPicController {
    @Autowired
    private UploadPicService uploadPicService;

    @RequestMapping("pic/upload")
    @ResponseBody
    public UploadResult uploadPic(MultipartFile uploadFile){
        return uploadPicService.uploadItemPic(uploadFile);
    }
}
