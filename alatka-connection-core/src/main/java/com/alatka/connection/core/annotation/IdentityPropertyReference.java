package com.alatka.connection.core.annotation;

import java.lang.annotation.*;

/**
 * 用于描述复杂对象，递归扫描对象中的{@link IdentityProperty}注解
 *
 * @author ybliu
 * @see com.alatka.connection.core.module.AbstractModuleBuilder#assignIdentity(Object, Class)
 * @see com.alatka.connection.core.module.AbstractModuleBuilder#identity
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IdentityPropertyReference {
}
