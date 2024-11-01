package com.mall.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageStorage {
    Integer id;
    String key;
    String name;
    String type;
    Long size;
    String url;
}
