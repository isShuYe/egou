package com.ego.manager.service.impl;

import com.ego.commons.FtpUtil;
import com.ego.commons.UploadResult;
import com.ego.manager.service.UploadPicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadPicServiceImpl implements UploadPicService {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basePath}")
    private String basePath;
    @Value("${ftp.filePath}")
    private String filePath;
    @Value("${ftp.http}")
    private String http;


    @Override
    public UploadResult uploadItemPic(MultipartFile file) {
        UploadResult ur = new UploadResult();
        try{
            String prefix = UUID.randomUUID().toString();
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String filename = prefix+suffix;
            boolean b = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, file.getInputStream());
//            boolean c = FtpUtil.downloadFile(host,port,username,password,basePath+filePath,"1.jpg","H:\\java");
//            System.out.println(c);
            if (b){
                ur.setError(0);
                ur.setUrl(http+filename);
                ur.setMessage("ok");
                return ur;
            }else{
                ur.setError(1);
                ur.setUrl("404");
                ur.setMessage("图片不存在");
                return ur;
            }

        }catch (Exception e){
            e.printStackTrace();
            return ur;
        }

    }
}
