package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.core.mybatis.enums.IdType;
import lombok.Data;


@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * 表字段： sys_user.id
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;

    /**
     * 用户名(登录名)
     * 表字段： sys_user.username
     */
    private String username;

    /**
     * 用户邮箱
     * 表字段： sys_user.email
     */
    private String email;

    /**
     * 密码salt
     * 表字段： sys_user.salt
     */
    private String salt;

    /**
     * 加salt mid5后的密码
     * 表字段： sys_user.password
     */
    private String password;

    /**
     * 是否是管理员 0不是 1是
     * 表字段： sys_user.is_admin
     */
    private String isAdmin;


}