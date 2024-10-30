package com.mall.common;

import lombok.Getter;

@Getter
public enum ResultEnum {
    UNKNOWN_ERROR(1001, "未知错误"),
    SUCCESS(400, "操作成功"),
    ILLEGAL_TOKEN(201, "token不合法"),
    USER_NOT_EXIST(301, "用户不存在"),
    WRONG_PASSWORD(302, "密码错误"),
    GOOD_NOT_EXIST(303, "商品不存在"),
    GOOD_IS_FROZEN(304, "商品已经被冻结"),
    ILLEGAL_INFO(305, "输入信息不合法"),
    OVER_RANGE(306,"购买商品超出库存"),
    REPEAT_INFO(307,"购买失败，同一件商品重复填入信息"),
    GOOD_IS_ACTIVE(308,"撤销贩卖失败，商品未被冻结"),
    HISTORY_NOT_EXIST(309,"历史信息不存在"),
    USER_HAS_EXIST(310,"用户名已存在"),
    ;

    private final int code; // 使用 final 修饰，确保值不可变
    private final String msg; // 使用 final 修饰，确保值不可变

    // 在构造函数中为属性赋值
    ResultEnum(int code, String msg) {
        this.code = code; // 将参数赋值给实例变量
        this.msg = msg;   // 将参数赋值给实例变量
    }
}