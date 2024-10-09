package com.mall.common;

import lombok.Getter;

@Getter
public enum StateEnum {
    ACTIVE(0),
    FROZEN(1)
    ;
    private final Integer state;
    StateEnum(Integer state) {
        this.state = state;
    }
}
