package com.mall.service.impl;

import com.mall.dao.ImageStorageDao;
import com.mall.entity.ImageStorage;
import com.mall.service.ImageStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    @Resource
    private ImageStorageDao imageStorageDao;

    @Override
    public Map<String, Object> store(ImageStorage imageStorage){
        if(imageStorageDao.store(imageStorage) > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("url", imageStorage.getUrl());
            map.put("key", imageStorage.getKey());
            map.put("id", imageStorage.getId());
            return map;
        }else{
            return null;
        }
    }
    @Override
    public ImageStorage findByKey(String key){ return imageStorageDao.findByKey(key); }
}
