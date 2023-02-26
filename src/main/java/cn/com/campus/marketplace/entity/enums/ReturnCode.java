package cn.com.campus.marketplace.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReturnCode {
    RC200(200, "请求成功"),
    RC201(201, "操作成功"),
    RC500(500, "系统异常，请稍后重试"),
    RC999(999, "请求失败"),
    ACCESS_DENIED(2003, "没有权限访问资源"),
    USERNAME_NOT_EXIST(1000, "用户名不存在"),
    USERNAME_EXIST(1001, "用户名已存在"),
    USERNAME_ERROR(1002,"用户名错误"),
    PASSWORD_ERROR(1003, "密码错误"),
    USER_DENIED(1004, "用户被禁用"),
    USER_NO_LOGIN(1005,"用户未登录"),
    ORDER_NO_EXIST(1006, "订单不存在");
    public final int code;
    public final String message;
}
