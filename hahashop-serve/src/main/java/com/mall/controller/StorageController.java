package com.mall.controller;

import com.mall.common.CheckUtil;
import com.mall.common.Result;
import com.mall.common.ResultUtil;
import com.mall.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Resource
    private StorageService storageService;
    @Resource
    private CheckUtil checkUtil;
    @Resource
    private ResultUtil resultUtil;



//    @RequestMapping("/upload")
//    public Result<Object> upload(@RequestParam("file") MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//
//
//
//
//
//    }



}
