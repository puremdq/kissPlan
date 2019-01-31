package com.aojiaoo.core.common;

public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),//成功
    ILLEGAL_ARGUMENT(203, "ILLEGAL_ARGUMENT"),//参数有误
    NEED_LOGIN(401, "NEED_LOGIN"),//需要登录
    NO_AUTH(403, "NO_AUTH"),//访问被拒绝 没有权限
    NOT_FOUND(404, "NOT_FOUND"),//未找到
    ERROR(500, "ERROR");//服务器内部发生错误//


    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
