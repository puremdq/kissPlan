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
@Table(name = "sys_menu")
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
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 菜单名称
     * 表字段： sys_menu.menu_name
     */
    @Column(name = "menu_name")
    private String menuName;
    /**
     * 菜单url
     * 表字段： sys_menu.menu_url
     */
    @Column(name = "menu_url")
    private String menuUrl;
    /**
     * 父级id
     * 表字段： sys_menu.parent_id
     */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 权限名称
     * 表字段： sys_menu.permission_name
     */
    @Column(name = "permission_name")
    private Integer permissionName;
    /**
     * 是否显示 1显示  0不显示
     * 表字段： sys_menu.is_show
     */
    @Column(name = "is_show")
    private String isShow;
    /**
     * 排序
     * 表字段： sys_menu.sort
     */
    @Column(name = "sort")
    private Integer sort;

}