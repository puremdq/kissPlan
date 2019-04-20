package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "kp_article_view")
public class ArticleView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public ArticleView() {
    }

    /**
     * 表字段： kp_article_view.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 用户id
     * 表字段： kp_article_view.author_id
     */
    @Column(name = "author_id")
    private Integer authorId;
    /**
     * 标题
     * 表字段： kp_article_view.title
     */
    @Column(name = "title")
    private String title;
    /**
     * 内容
     * 表字段： kp_article_view.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 预览
     * 表字段： kp_article_view.preview
     */
    @Column(name = "preview")
    private String preview;
    /**
     * 第一张图 用作展示用
     * 表字段： kp_article_view.first_img
     */
    @Column(name = "first_img")
    private String firstImg;
    /**
     * 点赞数
     * 表字段： kp_article_view.like_num
     */
    @Column(name = "like_num")
    private Integer likeNum;
    /**
     * 评论数
     * 表字段： kp_article_view.comment_num
     */
    @Column(name = "comment_num")
    private Integer commentNum;
    /**
     * 阅读数
     * 表字段： kp_article_view.read_num
     */
    @Column(name = "read_num")
    private Integer readNum;
    /**
     * 用户名(登录名)
     * 表字段： kp_article_view.author_name
     */
    @Column(name = "username")
    private String username;
    /**
     * 头像
     * 表字段： kp_article_view.author_avatars
     */
    @Column(name = "avatars")
    private String avatars;

    /**
     * 文章类型 0普通 | 1 markdown
     */
    @Column(name = "article_type")
    private Integer articleType;


    @Transient
    private Boolean isCurrentUserLiked;

}