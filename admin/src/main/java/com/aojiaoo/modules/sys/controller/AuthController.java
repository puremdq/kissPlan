package com.aojiaoo.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AuthController {


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }


    //登录失败
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }

        System.out.println("登录失败");
        return "login";
//
    }


}
