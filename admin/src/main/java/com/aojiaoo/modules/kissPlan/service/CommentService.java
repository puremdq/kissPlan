package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Comment;
import com.aojiaoo.modules.kissPlan.mapper.CommentMapper;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseService<Comment, CommentMapper> {

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean save(Comment entity) {

        if (articleService.get(entity.getArticleId()) == null) {
            logger.warn("试图id为{}的文章不存在", entity.getArticleId());
            return false;
        }

        entity.setAuthorId(UserUtil.getCurrentUser().getId());
        return super.save(entity);
    }


    public Page<Comment> getByArticleId(Integer id, Page<Comment> page) {

        if (!IdUtil.isValidId(id)) {
            return null;
        }

        Comment comment = new Comment();
        comment.setArticleId(id);
        page.setList(this.mapper.selectBySelective(comment, page));
        return page;
    }
}
