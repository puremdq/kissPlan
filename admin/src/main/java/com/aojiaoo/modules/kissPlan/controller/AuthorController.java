package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.kissPlan.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/u")
public class AuthorController extends BaseController {


    @Autowired
    private AuthorService authorService;

    @ResponseBody
    @GetMapping("{id}")
    public ServerResponse form(@PathVariable("id") Integer id) {

        if (id == null || id <= 0) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        return this.createServerResponse(authorService.getAuthorView(id));
    }

}
