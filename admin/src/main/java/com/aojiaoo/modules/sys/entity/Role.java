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
@Table(name = "sys_role")
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
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 角色名称
     * 表字段： sys_role.role_name
     */
    @Column(name = "role_name")
    private String roleName;

}