package com.alatka.connection.core.component.other;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.Property;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 内嵌组件注册器，直接new创建，非通过{@link SpringFactoriesLoader#FACTORIES_RESOURCE_LOCATION}创建
 *
 * @author ybliu
 */
public abstract class EmbeddedComponentRegister<T extends Property> extends AbstractComponentRegister<T, Void> {

    @Override
    public Void mappingKey() {
        return null;
    }
}
