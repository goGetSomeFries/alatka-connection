package com.alatka.connection.core.annotation;

import java.lang.annotation.*;

/**
 * 被注解字段值增加identity前缀
 *
 * @author ybliu
 * @see com.alatka.connection.core.module.AbstractModuleBuilder#assignIdentity(Object, Class)
 * @see com.alatka.connection.core.module.AbstractModuleBuilder#identity
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IdentityProperty {

    /**
     * TODO
     */
    boolean referenced() default true;
}
