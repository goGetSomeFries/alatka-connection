package com.alatka.connection.core.annotation;

import java.lang.annotation.*;

/**
 * TODO
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
