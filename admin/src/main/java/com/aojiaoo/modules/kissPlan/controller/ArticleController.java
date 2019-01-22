package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Article;
import com.aojiaoo.modules.kissPlan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @GetMapping("{id}")
    public ServerResponse form(@PathVariable("id") Integer id) {
        if (id == null || id <= 0) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        return ServerResponse.createBySuccess(this.articleService.get(id));
    }

    @ResponseBody
    @GetMapping("")
    public ServerResponse list(Page<Article> page, Article article) {
        return ServerResponse.createBySuccess(this.articleService.findPage(article, page));
    }

    @ResponseBody
    @PostMapping("save")
    public ServerResponse save(Article article) {
        this.articleService.save(article, true);
        return ServerResponse.createBySuccessMessage("保存成功");
    }


}
