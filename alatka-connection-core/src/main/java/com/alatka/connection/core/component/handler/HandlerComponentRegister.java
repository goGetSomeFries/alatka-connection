package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class HandlerComponentRegister<T extends HandlerProperty> extends AbstractComponentRegister<T, HandlerProperty.Type> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
        builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
        if (property.getParams() == null) {
            property.setParams(Collections.emptyMap());
        }
    }

    protected <S> S getParamsValue(Map<String, Object> params, String key, S defaultValue) {
        return (S) params.getOrDefault(key, defaultValue);
    }

    protected <S> S getParamsValue(Map<String, Object> params, String key) {
        return (S) params.get(key);
    }

    protected <S> S getParamsValueOrThrow(Map<String, Object> params, String key) {
        return (S) Optional.ofNullable(params.get(key))
                .orElseThrow(() -> new IllegalArgumentException(key + "must not be null"));
    }
}
