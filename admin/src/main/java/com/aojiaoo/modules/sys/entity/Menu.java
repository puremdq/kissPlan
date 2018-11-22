package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Menu() {
    }
    public Menu(Integer id) {
    this.id=id;
    }

    /**
     * 
     * 表字段： sys_menu.id
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    /**
     * 菜单名称
     * 表字段： sys_menu.menu_name
     */
    private String menuName;
    /**
     * 菜单url
     * 表字段： sys_menu.menu_url
     */
    private String menuUrl;
    /**
     * 父级id
     * 表字段： sys_menu.parent_id
     */
    private Integer parentId;
    /**
     * 权限名称
     * 表字段： sys_menu.permission_name
     */
    private Integer permissionName;
    /**
     * 是否显示 1显示  0不显示
     * 表字段： sys_menu.is_show
     */
    private String isShow;
    /**
     * 排序
     * 表字段： sys_menu.sort
     */
    private Integer sort;

}