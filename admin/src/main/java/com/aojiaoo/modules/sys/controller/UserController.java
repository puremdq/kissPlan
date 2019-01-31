package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/u")
public class UserController extends BaseController {

    @ResponseBody
    @RequestMapping("")
    public String test() {

        return "";
//        return this.service.selectByPrimaryKey("1").toString();
    }



    @ResponseBody
    @RequestMapping("detail")
    public String detail() {

        return "";
//        return this.service.selectByPrimaryKey("1").toString();
    }

}
