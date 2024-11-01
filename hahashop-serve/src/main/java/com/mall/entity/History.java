package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class History {
    private Integer historyId;//主键 数据库自增
    private Integer goodId;
    private String goodName;
    private String goodDesc;
    private String goodPrice;
    private String goodImage;
    private String operationTime;
}
