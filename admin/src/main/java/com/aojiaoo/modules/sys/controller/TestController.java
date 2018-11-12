package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.json.annotation.JSON;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.service.TestService;
import com.aojiaoo.modules.sys.service.UserService;
import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController<TestService> {

    @Autowired
    UserService userService;

    @ResponseBody
    @JsonFilter("createDate")
    @JSON(type = User.class,filter = "createDate")
    @RequestMapping("1")
    public List<User> test() {
        return userService.findList(new User());
    }

}
