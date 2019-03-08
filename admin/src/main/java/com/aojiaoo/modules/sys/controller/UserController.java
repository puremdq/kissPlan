package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.service.UserService;
import com.aojiaoo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("register")
    public ServerResponse register(User user) {

        Integer id = userService.register(user);
        if (id == null) {
            return ServerResponse.createByErrorMessage("注册失败,请检查您的输入是否正确");
        }

        return ServerResponse.createBySuccess(user);
    }


    @ResponseBody
    @PostMapping("checkUsername")
    public ServerResponse checkUsername(String username) {

        if (StringUtils.isBlank(username)) {
            return ServerResponse.createByError();
        }

        boolean tf = userService.getByUserName(username) == null;
        return createServerResponse(tf);
    }


    @ResponseBody
    @PostMapping("checkEmail")
    public ServerResponse checkEmail(String email) {
        boolean tf = userService.getByEmail(email) == null;
        return createServerResponse(tf);
    }
}
