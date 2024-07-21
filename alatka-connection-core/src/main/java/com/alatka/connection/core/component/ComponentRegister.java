package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;

/**
 * 组件注册器
 *
 * @param <T> {@link Property}
 * @author ybliu
 */
public interface ComponentRegister<T extends Property, S> extends ReferenceProperty<S> {

    /**
     * 注册组件为{@link org.springframework.beans.factory.config.BeanDefinition}
     *
     * @param property       {@link Property}
     * @param beanNamePrefix
     * @param custom
     * @return spring bean name
     */
    String register(T property, String beanNamePrefix, boolean custom);

}
