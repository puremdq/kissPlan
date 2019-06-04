package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.*;
import com.aojiaoo.modules.kissPlan.service.ArticleService;
import com.aojiaoo.modules.kissPlan.service.CommentService;
import com.aojiaoo.modules.sys.entity.User;
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
    public ServerResponse articleList(Page<ArticleView> page, Integer authorId) {
        page = articleService.indexArticleList(page, authorId);
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
        return createServerResponse(this.articleService.save(article));
    }

    /**
     * 得到文章评论
     *
     * @param id       文章id
     * @param page     page
     * @param sortType ,"value":"desc","equals":true,"description":"desc降序 asc 升序 此字段可不传 不传默认为降序"
     * @param sortBy   "value":"date","equals":true,"description": "date按照时间  hot按照热度（点赞数） 此字段可不传 不传默认按时间"
     * @return ServerResponse
     */
    @ResponseBody
    @GetMapping("getComment")
    public ServerResponse getComment(Integer id, Page<CommentView> page, String sortBy, String sortType) {

        if (!IdUtil.isValidId(id)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        if (StringUtils.isBlank(sortBy)) {
            sortBy = "date";
        }
        if (StringUtils.isBlank(sortType)) {
            sortType = "desc";
        }
        if (!StringUtils.findInSet(sortBy, "date,hot")) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        if (!StringUtils.findInSet(sortType, "desc,asc")) {
            return ServerResponse.createByErrorMessage("非法参数");
        }

        if (sortBy.equals("date")) {
            sortBy = "create_date";
        }
        if (sortBy.equals("hot")) {
            sortBy = "like_num";
        }
        Page<CommentView> commentPage = this.commentService.getCommentByArticleId(id, sortBy, sortType, page);
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


    /**
     * 得到文章评论
     *
     * @param id   文章id
     * @param page page
     * @return ServerResponse
     */
    @ResponseBody
    @GetMapping("getLikeRecord")
    public ServerResponse getLikeRecord(Integer id, Page<LikeRecordView> page) {

        if (!IdUtil.isValidId(id)) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        LikeRecordView likeRecordView = new LikeRecordView();
        likeRecordView.setArticleId(id);
        Page<LikeRecordView> res = this.articleService.getLikeRecord(likeRecordView, page);
        return ServerResponse.createBySuccess(res);
    }

}
