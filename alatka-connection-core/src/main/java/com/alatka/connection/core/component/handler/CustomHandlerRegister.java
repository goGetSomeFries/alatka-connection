package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 * @see CustomMessageHandler
 */
public class CustomHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_CLASS_NAME = "className";

    private static final String KEY_BEAN_NAME = "beanName";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        String beanName = this.getParamsValue(property.getParams(), KEY_BEAN_NAME);
        String className = this.getParamsValue(property.getParams(), KEY_CLASS_NAME);
        if (beanName == null && className == null) {
            throw new IllegalArgumentException("beanName and className must not be null both");
        }

        Class<?> clazz = ClassUtil.forName(beanName != null ? getBeanFactory().getBeanDefinition(beanName).getBeanClassName() : className);
        if (!CustomMessageHandler.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " must be an instance of " + CustomMessageHandler.class.getName());
        }

        if (beanName != null) {
            builder.addConstructorArgReference(beanName);
        } else {
            builder.addConstructorArgValue(ClassUtil.newInstance(className));
        }
        builder.addConstructorArgValue(CustomMessageHandler.METHOD_NAME);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.custom;
    }
}
