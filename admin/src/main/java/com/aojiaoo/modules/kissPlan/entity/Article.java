package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "kp_article")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Article() {
    }

    public Article(Integer id) {
        this.id=id;
    }

    /**
     * 
     * 表字段： kp_article.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 用户id
     * 表字段： kp_article.author_id
     */
    @Column(name = "author_id")
    private Integer authorId;
    /**
     * 标题
     * 表字段： kp_article.title
     */
    @Column(name = "title")
    private String title;
    /**
     * 内容
     * 表字段： kp_article.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 预览
     * 表字段： kp_article.preview
     */
    @Column(name = "preview")
    private String preview;
    /**
     * 第一张图 用作展示用
     * 表字段： kp_article.first_img
     */
    @Column(name = "first_img")
    private String firstImg;
    /**
     * 点赞数
     * 表字段： kp_article.like_num
     */
    @Column(name = "like_num")
    private Integer likeNum;
    /**
     * 评论数
     * 表字段： kp_article.comment_num
     */
    @Column(name = "comment_num")
    private Integer commentNum;
    /**
     * 阅读数
     * 表字段： kp_article.read_num
     */
    @Column(name = "read_num")
    private Integer readNum;

}