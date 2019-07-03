package com.justin.system.service;

import com.justin.system.entity.basic.ResponseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public ResponseWrapper saveFile(MultipartFile file) {
        return ResponseWrapper.successRender(file.getOriginalFilename());
    }

    public ResponseWrapper getFileList() {
        return ResponseWrapper.successRender("get file successfully");
    }
}
