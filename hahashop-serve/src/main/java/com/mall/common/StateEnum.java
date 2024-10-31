package com.mall.common;

import lombok.Getter;

@Getter
public enum StateEnum {
    ACTIVE(0,"进行中"),
    CANCELED(1,"已完成"),
    COMPLETE(2,"已取消");
    ;
    private final Integer state;
    private final String description;

    StateEnum(Integer state, String description) {
        this.state = state;
        this.description = description;
    }
}
