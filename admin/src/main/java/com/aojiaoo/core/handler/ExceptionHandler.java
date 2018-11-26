package com.aojiaoo.core.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof org.apache.shiro.authz.UnauthorizedException || e instanceof org.apache.shiro.authz.UnauthenticatedException) {
            modelAndView.setViewName("redirect:/static/403.html");
            modelAndView.setStatus(HttpStatus.FORBIDDEN);
            return modelAndView;
        }
        e.printStackTrace();
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.setViewName("redirect:/static/500.html");
        return modelAndView;
    }
}