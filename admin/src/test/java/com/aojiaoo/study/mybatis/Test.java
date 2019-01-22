package com.aojiaoo.study.mybatis;

import com.aojiaoo.core.base.BaseEntity;
import com.aojiaoo.core.mybatis.UUIdGenId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "a_test")
public class Test extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Test() {
    }

    public Test(String id) {
        this.id=id;
    }

    /**
     * 1
     * 表字段： a_test.id
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "id")
    private String id;
    /**
     * 1
     * 表字段： a_test.name
     */
    @Column(name = "name")
    private String name;

}