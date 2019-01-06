package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Integer id) {
        this.id=id;
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
    /**
     * 密码salt
     * 表字段： sys_user.salt
     */
    @Column(name = "salt")
    private String salt;
    /**
     * 加salt mid5后的密码
     * 表字段： sys_user.password
     */
    @Column(name = "password")
    private String password;
    /**
     * 是否是管理员 0不是 1是
     * 表字段： sys_user.is_admin
     */
    @Column(name = "is_admin")
    private String isAdmin;

}