package com.aojiaoo.core.advice;

import com.aojiaoo.core.json.annotation.JSON;
import com.aojiaoo.modules.sys.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethodAnnotation(JSON.class) != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new User();
//        JSON json = methodParameter.getMethodAnnotation(JSON.class);
//        if (json == null) {
//            return o;
//        }
//
//        CustomerJsonSerializer cjs = new CustomerJsonSerializer();
//        cjs.filter(json.type(), json.include(), json.filter());
//        try {
//            String res = cjs.toJson(o);
//            return res;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return o;
    }
}