package com.mall.common;

import lombok.Getter;

@Getter
public enum ResultEnum {
    UNKNOWN_ERROR(1001, "未知错误"),
    SUCCESS(400, "操作成功"),
    ILLEGAL_TOKEN(201, "token不合法"),
    USER_NOT_EXIST(301, "用户不存在"),
    WRONG_PASSWORD(302, "密码错误"),
    EXAMPLE_NOT_EXIST(303, "操作对象找不到了"),
    USER_HAS_EXIST(304,"用户名已存在"),
    GOOD_IS_SELLOUT(305, "商品已经卖完啦"),
    ILLEGAL_INFO(306, "输入信息不合法"),
    OVER_RANGE(307,"购买商品超出库存"),
    ;

    private final int code; // 使用 final 修饰，确保值不可变
    private final String msg; // 使用 final 修饰，确保值不可变

    // 在构造函数中为属性赋值
    ResultEnum(int code, String msg) {
        this.code = code; // 将参数赋值给实例变量
        this.msg = msg;   // 将参数赋值给实例变量
    }
}