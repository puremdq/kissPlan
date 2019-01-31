package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import com.aojiaoo.modules.kissPlan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页controller 首页下所有url不用登录
 */
@Controller
@RequestMapping("/index")
public class indexController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "slideshow", method = RequestMethod.GET)
    public ServerResponse index() {
        Map<String, Object> res = new HashMap<>();
        res.put("slideshow", articleService.getSlideshowArticle(5));
        return ServerResponse.createBySuccess(res);
    }


    /**
     * 查看首页文章
     *
     * @param page
     * @return
     */
    @ResponseBody
    @GetMapping("article")
    public ServerResponse articleList(Page<ArticleView> page) {
        page = articleService.indexArticleList(page);
        return ServerResponse.createBySuccess(page);
    }

}
