package com.alatka.connection.core.annotation;

import java.lang.annotation.*;

/**
 * TODO 名称优化
 *
 * @author ybliu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IdentityProperty {

    boolean referenced() default true;
}
