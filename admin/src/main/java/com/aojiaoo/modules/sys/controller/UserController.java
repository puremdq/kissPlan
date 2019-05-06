package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.annotations.IgnoreInField;
import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.kissPlan.service.UserDetailService;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.service.UserService;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/u")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService authorService;

    @ResponseBody
    @PostMapping("register")
    public ServerResponse register(User user) {

        Integer id = userService.register(user);
        if (id == null) {
            return ServerResponse.createByErrorMessage("注册失败,请检查您的输入是否正确");
        }

        return ServerResponse.createBySuccess(user);
    }


    /**
     * 检查用户名和密码是否存在
     *
     * @param value        username
     * @param identifyType email|username
     * @return ServerResponse
     */
    @ResponseBody
    @PostMapping("check")
    public ServerResponse checkUsername(String value, String identifyType) {

        if (StringUtils.isAnyBlank(value, identifyType)) {
            return ServerResponse.createByError();
        }

        if (!StringUtils.equalsAny(identifyType, GlobalProperties.IDENTIFY_TYPE_USERNAME, GlobalProperties.IDENTIFY_TYPE_EMAIL)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        if (GlobalProperties.IDENTIFY_TYPE_USERNAME.equals(identifyType)) {
            if (userService.getByUserName(value) != null) {
                return ServerResponse.createByErrorMessage("用户名已存在");
            }
        }

        if (GlobalProperties.IDENTIFY_TYPE_EMAIL.equals(identifyType)) {
            if (userService.getByEmail(value) != null) {
                return ServerResponse.createByErrorMessage("邮箱已存在");
            }
        }
        return ServerResponse.createBySuccess("该名称可用");
    }


    @ResponseBody
    @GetMapping("{id}")
    public ServerResponse form(@PathVariable("id") Integer id) {

        if (id == null || id <= 0) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        return this.createServerResponseNotFoundOrSuccess(authorService.getAuthorView(id));
    }

    @ResponseBody
    @GetMapping("currentUser")
    public ServerResponse currentUser() {
        if (!UserUtil.isAuthenticated()) {
            return ServerResponse.createByErrorMessage("非法操作");
        }
        return ServerResponse.createBySuccess(authorService.getAuthorView(UserUtil.getCurrentUserId()));
    }


    @ResponseBody
    @GetMapping("save")

    @IgnoreInField(type = User.class, field = "id")
    public ServerResponse save(User user) {
        return null;
    }
}
