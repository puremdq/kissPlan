package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class indexController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        System.out.println(UserUtil.getCurrentUser());
        return "index";
    }

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
        System.out.println("登录失败");
        return "login";
//
    }


    @ResponseBody
    @RequiresPermissions("dsd")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {

//        System.out.println(SecurityUtils.getSubject().hasRole("sdsds"));

        return "test";
//
    }

    @ResponseBody
    @RequiresPermissions("sdsd")
    @RequestMapping(value = "testP", method = RequestMethod.GET)
    public String testP() {
        System.out.println(SecurityUtils.getSubject().hasRole("sdsds"));
        return "test";
//
    }

}
