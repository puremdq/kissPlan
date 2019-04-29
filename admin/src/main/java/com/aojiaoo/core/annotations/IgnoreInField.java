package com.aojiaoo.core.annotations;

import java.lang.annotation.*;

/*忽略参数绑定的字段*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreInField {
    String field() default "";

    Class type();
}
