package com.mall.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class History {
    Integer historyId;//主键 数据库自增
    Integer goodId;
    String goodName;
    String goodDesc;
    String goodPrice;
    String goodImage;
    String operationTime;
}
