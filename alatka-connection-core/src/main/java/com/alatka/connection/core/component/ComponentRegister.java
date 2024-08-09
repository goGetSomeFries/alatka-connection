package com.alatka.connection.core.component;

import com.alatka.connection.core.property.core.Property;

/**
 * 组件注册器<br>
 * TODO 什么是组件
 *
 * @param <T> {@link Property}
 * @param <S>
 * @author ybliu
 */
public interface ComponentRegister<T extends Property, S> {

    /**
     * 注册组件为{@link org.springframework.beans.factory.config.BeanDefinition}
     *
     * @param property {@link Property}
     * @return spring bean name
     */
    String register(T property);

    /**
     * {@link ComponentRegister}映射标识
     *
     * @return 映射标识
     */
    S mappingKey();
}
