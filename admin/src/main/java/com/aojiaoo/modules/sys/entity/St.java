package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "a_test")
public class St extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public St() {
    }

    public St(String id) {
        this.id=id;
    }

    /**
     * 1
     * 表字段： a_test.id
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private String id;
    /**
     * 1
     * 表字段： a_test.name
     */
    @Column(name = "name")
    private String name;

}