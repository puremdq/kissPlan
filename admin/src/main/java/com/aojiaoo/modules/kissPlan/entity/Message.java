package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "kp_message")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    /**
     * 表字段： kp_message.id
     */
    @Id
    @KeySql(useGeneratedKeys = true)//回显id
    @Column(name = "id")
    private Integer id;
    /**
     * 表字段： kp_message.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 谁发送的
     * 表字段： kp_message.from
     */
    @Column(name = "`from`")
    private Integer from;
    /**
     * 发送给谁
     * 表字段： kp_message.to
     */
    @Column(name = "`to`")
    private Integer to;
    /**
     * 是否已读 1 已读 0未读
     * 表字段： kp_message.is_read
     */
    @Column(name = "is_read")
    private Integer isRead;


    @Transient
    private String fromUsername;//发送者名字


    @Transient
    private String fromAvatars;//发送者头像

    @Transient
    private String toUsername; //发送给谁的名字


    @Transient
    private String toAvatars;//发送给谁头像


    public String getFromUsername() {
        if (StringUtils.isNotBlank(fromUsername)) {
            return fromUsername;
        }

        return this.getFormUser().getUsername();
    }


    public String getFromAvatars() {
        if (StringUtils.isNotBlank(fromAvatars)) {
            return fromAvatars;
        }

        return StringUtils.trimToEmpty(this.getFormUser().getAvatars());
    }

    public String getToUsername() {
        if (StringUtils.isNotBlank(toUsername)) {
            return toUsername;
        }

        return this.getToUser().getUsername();
    }

    public String getToAvatars() {

        if (StringUtils.isNotBlank(toAvatars)) {
            return toAvatars;
        }
        return StringUtils.trimToEmpty(this.getToUser().getAvatars());
    }


    @JsonIgnore
    private User getFormUser() {
        User user = UserUtil.getUserById(this.from);
        return user == null ? new User() : user;
    }

    @JsonIgnore
    private User getToUser() {
        User user = UserUtil.getUserById(this.to);
        return user == null ? new User() : user;
    }

}