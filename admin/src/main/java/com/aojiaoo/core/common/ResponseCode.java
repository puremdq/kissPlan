package com.aojiaoo.core.common;

public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),//成功
    ILLEGAL_ARGUMENT(203, "ILLEGAL_ARGUMENT"),//成功
    NEED_LOGIN(401, "NEED_LOGIN"),//需要登录
    NO_AUTH(403, "NO_AUTH"),//服务器内部发生错误
    ERROR(500, "ERROR");//访问被拒绝 没有权限


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
