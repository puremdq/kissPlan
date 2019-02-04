package com.aojiaoo.modules.sys.controller;

import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.JsonUtil;
import com.aojiaoo.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 首页controller 首页下所有url不用登录
 */
@Controller
@RequestMapping("")
public class CommonController {

    @ResponseBody
    @RequestMapping(value = "NotFound", method = RequestMethod.GET)
    public void NotFound(HttpServletResponse response) {
        ServerResponse serverResponse = ServerResponse.createByResponseCode(ResponseCode.NOT_FOUND);
        WebUtils.writeBody(response, JsonUtil.toJson(serverResponse));
    }

}
