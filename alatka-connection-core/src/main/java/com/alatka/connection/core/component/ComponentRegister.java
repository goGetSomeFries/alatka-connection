package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * 组件注册器
 *
 * @param <T>
 */
public interface ComponentRegister<T extends Property> {

    String register(T property, String beanNamePrefix, boolean custom);

    void setBeanDefinitionRegistry(BeanDefinitionRegistry registry);
}
