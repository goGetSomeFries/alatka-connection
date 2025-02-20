package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.LockRegistryProperty;
import org.springframework.integration.support.locks.DefaultLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.integration.support.locks.PassThruLockRegistry;

/**
 * {@link LockRegistry}组件注册器
 *
 * @author ybliu
 * @see LockRegistry
 */
public class LockRegistryRegister extends MultiTypeSupportComponentRegister<LockRegistryProperty> {

    @Override
    public Class<LockRegistryProperty> mappingKey() {
        return LockRegistryProperty.class;
    }

    @Override
    protected void initialize() {
        initMap();

        initComponentClass(LockRegistryProperty.Type.fallback, DefaultLockRegistry.class);
        initComponentInit(LockRegistryProperty.Type.fallback, (builder, params) -> {
            LockRegistryProperty.Fallback fallback = (LockRegistryProperty.Fallback) params;
            if (fallback.getMask() != null) {
                builder.addConstructorArgValue(fallback.getMask());
            }
        });

        initComponentClass(LockRegistryProperty.Type.passthrough, PassThruLockRegistry.class);
        initComponentInit(LockRegistryProperty.Type.passthrough, (builder, params) -> {
        });
    }

}
