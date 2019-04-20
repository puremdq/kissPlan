package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "kp_comment_view")
public class CommentView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public CommentView() {
    }

    /**
     * 
     * 表字段： kp_comment_view.id
     */
    @Column(name = "id")
    private Integer id;
    /**
     * 发出该评论的用户id
     * 表字段： kp_comment_view.author_id
     */
    @Column(name = "author_id")
    private Integer authorId;
    /**
     * 用户名(登录名)
     * 表字段： kp_comment_view.author_name
     */
    @Column(name = "username")
    private String username;
    /**
     * 头像
     * 表字段： kp_comment_view.author_avatars
     */
    @Column(name = "avatars")
    private String avatars;
    /**
     * 
     * 表字段： kp_comment_view.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 
     * 表字段： kp_comment_view.article_id
     */
    @Column(name = "article_id")
    private Integer articleId;
    /**
     * 0表示顶级  其他则表示回复此id评论
     * 表字段： kp_comment_view.pid
     */
    @Column(name = "pid")
    private Integer pid;
    /**
     * 点赞数
     * 表字段： kp_comment_view.like_num
     */
    @Column(name = "like_num")
    private Integer likeNum;
    /**
     * 回复次数
     * 表字段： kp_comment_view.reply_num
     */
    @Column(name = "reply_num")
    private Integer replyNum;

    @Transient
    private List<CommentView> childCommentList;

}