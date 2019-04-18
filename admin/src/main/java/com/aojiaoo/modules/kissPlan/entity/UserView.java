package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "kp_user_view")
public class UserView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public UserView() {
    }

    /**
     * id
     * 表字段： kp_author_view.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 用户名(登录名)
     * 表字段： kp_author_view.author_name
     */
    @Column(name = "username")
    private String username;
    /**
     * 用户邮箱
     * 表字段： kp_author_view.email
     */
    @Column(name = "email")
    private String email;
    /**
     * 头像
     * 表字段： kp_author_view.avatars
     */
    @Column(name = "avatars")
    private String avatars;
    /**
     * 备注信息
     * 表字段： kp_author_view.remarks
     */
    @Column(name = "remarks")
    private String remarks;
    /**
     * 关注数
     * 表字段： kp_author_view.follow_num
     */
    @Column(name = "follow_num")
    private Integer followNum;
    /**
     * 粉丝数
     * 表字段： kp_author_view.fans_num
     */
    @Column(name = "fans_num")
    private Integer fansNum;
    /**
     * 文章数
     * 表字段： kp_author_view.article_num
     */
    @Column(name = "article_num")
    private Integer articleNum;
    /**
     * 总共写了多少字
     * 表字段： kp_author_view.words_num
     */
    @Column(name = "words_num")
    private Integer wordsNum;
    /**
     * 收获点赞数
     * 表字段： kp_author_view.be_like_num
     */
    @Column(name = "be_like_num")
    private Integer beLikeNum;

}