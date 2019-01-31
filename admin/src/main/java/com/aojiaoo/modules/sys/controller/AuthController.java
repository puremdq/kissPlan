package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.JsonUtil;
import com.aojiaoo.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class AuthController {


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletResponse response) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        WebUtils.writeBody(response, JsonUtil.toJson(ServerResponse.createByResponseCode(ResponseCode.NEED_LOGIN)));
        return null;
    }


    //登录失败

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(HttpServletResponse response) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        WebUtils.writeBody(response, JsonUtil.toJson(ServerResponse.createByErrorMessage("登录失败")));
        return null;
    }


}
