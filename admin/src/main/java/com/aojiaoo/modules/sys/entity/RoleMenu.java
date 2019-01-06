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
@Table(name = "sys_role_menu")
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
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 权限ID
     * 表字段： sys_role_menu.menu_id
     */
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

}