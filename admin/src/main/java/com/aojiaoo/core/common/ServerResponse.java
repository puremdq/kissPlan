package com.aojiaoo.core.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
public class ServerResponse implements Serializable {

    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 使用了类上面那个注解，在这种情况下，只返回status，不会有msg和data
     *
     * @param status status
     */
    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 成功，返回一个status。
     */
    public static ServerResponse createBySuccess() {
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 成功，返回一个文本供前端提示使用
     */
    public static ServerResponse createBySuccessMessage(String msg) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 成功，返回对应的数据
     */
    public static ServerResponse createBySuccess(Object data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 成功，把消息和数据都返回
     */
    public static ServerResponse createBySuccess(String msg, Object data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败，返回一个status
     */
    public static ServerResponse createByError() {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    /**
     * 失败，返回一个文本供前端提示使用
     */
    public static ServerResponse createByErrorMessage(String errorMessage) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), errorMessage);
    }

    /**
     * 失败，返回一个status和提示
     */
    public static ServerResponse createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse(errorCode, errorMessage);
    }


    public static ServerResponse createByResponseCode(ResponseCode responseCode) {
        return new ServerResponse(responseCode.getCode(), responseCode.getDesc());
    }

    public static ServerResponse createByResponseCode(ResponseCode responseCode, Object data) {
        return new ServerResponse(responseCode.getCode(), responseCode.getDesc(), data);
    }

}
