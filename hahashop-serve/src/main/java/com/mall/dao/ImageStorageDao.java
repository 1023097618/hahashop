package com.mall.dao;

import com.mall.entity.ImageStorage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.Map;
import java.util.Objects;

@Mapper
public interface ImageStorageDao {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer store(ImageStorage imageStorage);

    ImageStorage findByKey(String key);

}
