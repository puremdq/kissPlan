package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "sys_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId=userId;
        this.roleId=roleId;
    }

    /**
     * 用户ID
     * 表字段： sys_user_role.user_id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 角色ID
     * 表字段： sys_user_role.role_id
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

}