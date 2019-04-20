package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "kp_like_record_view")
public class LikeRecordView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public LikeRecordView() {
    }

    /**
     * 
     * 表字段： kp_like_record_view.user_id
     */
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 
     * 表字段： kp_like_record_view.article_id
     */
    @Column(name = "article_id")
    private Integer articleId;
    /**
     * 用户名(登录名)
     * 表字段： kp_like_record_view.username
     */
    @Column(name = "username")
    private String username;
    /**
     * 头像
     * 表字段： kp_like_record_view.avatars
     */
    @Column(name = "avatars")
    private String avatars;

}