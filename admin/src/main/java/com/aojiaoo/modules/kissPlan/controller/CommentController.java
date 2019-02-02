package com.aojiaoo.modules.kissPlan.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.kissPlan.entity.Comment;
import com.aojiaoo.modules.kissPlan.service.CommentService;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论文章
     *
     * @return ServerResponse
     */
    @ResponseBody
    @PostMapping("add")
    public ServerResponse add(Comment comment) {

        if (IdUtil.isValidId(comment.getId())) {
            ServerResponse.createByErrorMessage("非法的操作");
        }

        if (StringUtils.isBlank(comment.getContent()) || !IdUtil.isValidId(comment.getArticleId())) {
            ServerResponse.createByErrorMessage("非法的操作");
        }
        commentService.save(comment);
        return ServerResponse.createBySuccess();
    }



}
