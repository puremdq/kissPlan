package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Article;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import com.aojiaoo.modules.kissPlan.entity.Comment;
import com.aojiaoo.modules.kissPlan.service.ArticleService;
import com.aojiaoo.modules.kissPlan.service.CommentService;
import com.aojiaoo.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    /**
     * 查看首页文章
     *
     * @param page
     * @return
     */
    @ResponseBody
    @GetMapping("")
    public ServerResponse articleList(Page<ArticleView> page) {
        page = articleService.indexArticleList(page);
        return ServerResponse.createBySuccess(page);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ServerResponse form(@PathVariable("id") Integer id) {
        if (id == null || id <= 0) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        return this.createServerResponse(this.articleService.getArticleView(id));
    }


    @ResponseBody
    @PostMapping("save")
    public ServerResponse save(Article article) {
        this.articleService.save(article, true);
        return ServerResponse.createBySuccessMessage("保存成功");
    }

    @ResponseBody
    @GetMapping("getComment")
    public ServerResponse getComment(Integer id, Page<Comment> page) {

        if (!IdUtil.isValidId(id)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        Page<Comment> commentPage = commentService.getByArticleId(id, page);
        return this.createServerResponse(commentPage);
    }

}
