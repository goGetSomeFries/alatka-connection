package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.MultiTypeSupportProperty;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class MultiTypeSupportComponentRegister<T extends MultiTypeSupportProperty> extends SupportComponentRegister<T> {

    private Map<MultiTypeSupportProperty.Type, Class<?>> componentClassMap;

    private Map<MultiTypeSupportProperty.Type, BiConsumer<BeanDefinitionBuilder, MultiTypeSupportProperty.Params>> componentInitMap;

    @Override
    protected final void doRegister(BeanDefinitionBuilder builder, T property) {
        MultiTypeSupportProperty.Type type = property.valueOf(property.getType());
        MultiTypeSupportProperty.Params params = property.getParams() == null ?
                null : JsonUtil.convertToObject(property.getParams(), type.getClazz());

        componentInitMap.get(type).accept(builder, params);
    }

    @Override
    protected final Class<?> componentClass(T property) {
        return componentClassMap.get(property.valueOf(property.getType()));
    }

    protected void initMap() {
        componentClassMap = new HashMap<>();
        componentInitMap = new HashMap<>();
    }

    protected void initComponentClass(MultiTypeSupportProperty.Type key, Class<?> value) {
        componentClassMap.put(key, value);
    }

    protected void initComponentInit(MultiTypeSupportProperty.Type key, BiConsumer<BeanDefinitionBuilder, MultiTypeSupportProperty.Params> value) {
        componentInitMap.put(key, value);
    }

}
