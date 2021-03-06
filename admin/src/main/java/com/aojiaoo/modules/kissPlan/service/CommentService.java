package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Comment;
import com.aojiaoo.modules.kissPlan.entity.CommentView;
import com.aojiaoo.modules.kissPlan.mapper.CommentMapper;
import com.aojiaoo.modules.kissPlan.mapper.CommentViewMapper;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService extends BaseService<Comment, CommentMapper> {


    @Resource
    private CommentViewMapper commentViewMapper;

    /**
     * 根据文章id得到评论
     *
     * @param articleId
     * @param page
     * @return
     */
    public Page<CommentView> getCommentByArticleId(Integer articleId, String sortBy, String sortType, Page<CommentView> page) {

        if (!IdUtil.isValidId(articleId)) {
            return null;
        }

        CommentView comment = new CommentView();
        comment.setArticleId(articleId);
        comment.setPid(0);
        comment.setPid(0);
        comment.setSortBy(sortBy);
        comment.setSortType(sortType);


        List<CommentView> list = this.commentViewMapper.selectBySelective(page, comment);
        list.forEach(commentView ->
                commentView.setChildCommentList(getChildCommentList(commentView.getId()))
        );
        page.setList(list);
        return page;
    }

    /**
     * 放入评论的子评论
     *
     * @param pid 查找pid为传入值 的评论
     */
    private List<CommentView> getChildCommentList(Integer pid) {

        if (!IdUtil.isValidId(pid)) {
            return null;
        }
        CommentView temp = new CommentView();
        temp.setPid(pid);

        return this.commentViewMapper.selectBySelective(temp);
    }
}
