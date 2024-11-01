package com.mall.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageStorage {
    private Integer id;
    private String key;
    private String name;
    private String type;
    private Long size;
    private String url;
}
