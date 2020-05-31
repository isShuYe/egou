package com.ego.manager.service;

import com.ego.commons.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadPicService {
    UploadResult uploadItemPic(MultipartFile file);
}
