package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Table(name = "sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    /**
     * id
     * 表字段： sys_user.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 用户名(登录名)
     * 表字段： sys_user.username
     */
    @Column(name = "username")
    private String username;
    /**
     * 用户邮箱
     * 表字段： sys_user.email
     */
    @Column(name = "email")
    private String email;

    //头像
    @Column(name = "avatars")
    private String avatars;
    /**
     * 密码salt
     * 表字段： sys_user.salt
     */
    @JsonIgnore
    @Column(name = "salt")
    private String salt;
    /**
     * 加salt mid5后的密码
     * 表字段： sys_user.password
     */
    @JsonIgnore
    @Column(name = "password")
    private String password;

}