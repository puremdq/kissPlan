package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "kp_comment")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    /**
     * 表字段： kp_comment.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 发出该评论的用户id
     * 表字段： kp_comment.author_id
     */
    @Column(name = "author_id")
    private Integer authorId;
    /**
     * 表字段： kp_comment.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 表字段： kp_comment.article_id
     */
    @Column(name = "article_id")
    private Integer articleId;
    /**
     * 0表示顶级  其他则表示回复此id评论
     * 表字段： kp_comment.pid
     */
    @Column(name = "pid")
    private Integer pid;
    /**
     * 点赞数
     * 表字段： kp_comment.like_num
     */
    @Column(name = "like_num")
    private Integer likeNum;
    /**
     * 回复次数
     * 表字段： kp_comment.reply_num
     */
    @Column(name = "reply_num")
    private Integer replyNum;


}