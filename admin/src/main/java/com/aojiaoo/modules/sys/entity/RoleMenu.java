package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@Data
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public RoleMenu() {
    }
    public RoleMenu(Integer roleId, Integer menuId) {
    this.roleId=roleId;
        this.menuId=menuId;
    }

    /**
     * 角色ID
     * 表字段： sys_role_menu.role_id
     */
    @TableId(type = IdType.INPUT, value = "role_id")
    private Integer roleId;
    /**
     * 权限ID
     * 表字段： sys_role_menu.menu_id
     */
    @TableId(type = IdType.INPUT, value = "menu_id")
    private Integer menuId;

}