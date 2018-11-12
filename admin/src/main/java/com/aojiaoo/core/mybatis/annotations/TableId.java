package com.aojiaoo.core.mybatis.annotations;


import com.aojiaoo.core.mybatis.enums.IdType;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableId {

    /**
     * <p>
     * 字段值（驼峰命名方式，该值可无）
     * </p>
     */
    String value() default "";
    IdType type() default IdType.UUID;
}
