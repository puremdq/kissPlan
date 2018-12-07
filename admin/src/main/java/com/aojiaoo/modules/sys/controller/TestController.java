package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.annotations.Log;
import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.service.TestService;
import com.aojiaoo.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController<TestService> {

    @Autowired
    UserService userService;

    @Log(desc = "sfdasd")
    @ResponseBody
    @RequestMapping("1")
    public List<User> test(User user) {
        return test1();
    }

    @ResponseBody
    @RequestMapping("2")
    public List<User> test1() {
        return userService.findList(new User());
    }


    @ResponseBody
    @RequestMapping("3")
    public ServerResponse test3() {
        return ServerResponse.createByResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
    }


    @ResponseBody
    @RequestMapping("5")
    public ServerResponse test5(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }

        return ServerResponse.createByResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
    }


}
