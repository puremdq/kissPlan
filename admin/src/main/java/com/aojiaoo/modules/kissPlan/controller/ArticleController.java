package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Article;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import com.aojiaoo.modules.kissPlan.entity.Comment;
import com.aojiaoo.modules.kissPlan.entity.CommentView;
import com.aojiaoo.modules.kissPlan.service.ArticleService;
import com.aojiaoo.modules.kissPlan.service.CommentService;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;


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

    /**
     * 查看文章详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("{id}")
    public ServerResponse detail(@PathVariable("id") Integer id) {
        if (id == null || id <= 0) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        return this.createServerResponseNotFoundOrSuccess(this.articleService.getArticleView(id));
    }

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    @ResponseBody
    @PostMapping("save")
    public ServerResponse save(Article article) {
        return createServerResponse(this.articleService.save(article, true));
    }

    /**
     * 得到文章评论
     *
     * @param id   文章id
     * @param page page
     * @return ServerResponse
     */
    @ResponseBody
    @GetMapping("getComment")
    public ServerResponse getComment(Integer id, Page<CommentView> page) {

        if (!IdUtil.isValidId(id)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        Page<CommentView> commentPage = this.articleService.getCommentByArticleId(id, page);
        return ServerResponse.createBySuccess(commentPage);
    }

    /**
     * 发表评论
     *
     * @param comment comment
     * @return
     */
    @ResponseBody
    @PostMapping("reply")
    public ServerResponse reply(Comment comment) {

        if (IdUtil.isValidId(comment.getId())) {
            ServerResponse.createByErrorMessage("非法的操作");
        }

        if (StringUtils.isBlank(comment.getContent()) || !IdUtil.isValidId(comment.getArticleId())) {
            ServerResponse.createByErrorMessage("非法的操作");
        }
        return createServerResponse(articleService.reply(comment));
    }


    /**
     * 点赞
     *
     * @param id       文章id
     * @param isCancel boolean 是否是取消赞
     * @return ServerResponse
     */
    @ResponseBody
    @PostMapping("like")
    public ServerResponse like(Integer id, boolean isCancel) {

        if (!IdUtil.isValidId(id)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        return createServerResponse(this.articleService.doLike(id, isCancel));
    }

}
