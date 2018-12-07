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
