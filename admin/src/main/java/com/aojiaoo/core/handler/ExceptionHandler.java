package com.aojiaoo.core.handler;

import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.JsonUtil;
import com.aojiaoo.utils.WebUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        if (e instanceof org.apache.shiro.authz.UnauthorizedException || e instanceof org.apache.shiro.authz.UnauthenticatedException) {
//          ModelAndView modelAndView = new ModelAndView();
//          modelAndView.setViewName("redirect:/static/403.html");
//          modelAndView.setStatus(HttpStatus.FORBIDDEN);
//          return modelAndView;
            WebUtils.writeBody(response, JsonUtil.toJson(ServerResponse.createByResponseCode(ResponseCode.NO_AUTH)));
            return null;
        }

        e.printStackTrace();
//        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        modelAndView.setViewName("redirect:/static/500.html");
//        return modelAndView;
        WebUtils.writeBody(response, JsonUtil.toJson(ServerResponse.createByResponseCode(ResponseCode.ERROR)));
        return null;
    }


}