package com.justin.system.controller;

import com.justin.system.entity.basic.ResponseWrapper;
import com.justin.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseWrapper saveFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return fileService.saveFile(file);
    }

    @GetMapping("/list")
    public ResponseWrapper getFileList() {
        return fileService.getFileList();
    }
}
