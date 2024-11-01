package com.mall.service;


import com.mall.entity.ImageStorage;

import java.util.Map;

public interface ImageStorageService {
    Map<String, Object> store(ImageStorage imageStorage);
    ImageStorage findByKey(String key);
}
