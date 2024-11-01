package com.mall.common;

import org.springframework.stereotype.Component;

@Component
public class StateChangeUtil {
    public Integer StateChange(StateEnum stateEnum) {
        return stateEnum.getState();
    }
}
