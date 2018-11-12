package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@Data
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Role() {
    }
    public Role(Integer id) {
    this.id=id;
    }

    /**
     * 
     * 表字段： sys_role.id
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    /**
     * 角色名称
     * 表字段： sys_role.role_name
     */
    private String roleName;

}