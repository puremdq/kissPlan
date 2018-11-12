package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.core.mybatis.enums.IdType;
import lombok.Data;

@Data
public class Test extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Test() {
    }




    /**
     * 1
     * 表字段： a_test.id
     */
    @TableId(type = IdType.UUID)
    private String id;


    /**
     * 1
     * 表字段： a_test.name
     */
    private String name;


}