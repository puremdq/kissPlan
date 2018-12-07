package com.aojiaoo.core.advice;

import com.aojiaoo.core.common.ServerResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object resposeData, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (!(resposeData instanceof ServerResponse)) {
            return resposeData;
        }
        ServerResponse serverResponseData = (ServerResponse) resposeData;

        HttpStatus httpStatus = HttpStatus.resolve(serverResponseData.getStatus());
        httpStatus = httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus;

        serverHttpResponse.setStatusCode(httpStatus);
        return serverResponseData;
    }
}