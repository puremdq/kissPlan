package com.aojiaoo.core.mybatis.annotations;

import java.lang.annotation.*;

/**
 * 标识MyBatis的DAO,方便{@link MyBatisDao}的扫描。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyBatisDao {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";

}