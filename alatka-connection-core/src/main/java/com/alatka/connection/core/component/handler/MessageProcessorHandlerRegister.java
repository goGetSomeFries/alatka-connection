package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class MessageProcessorHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

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
        if (!this.handlerClass().isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " must be an instance of " + this.handlerClass().getName());
        }

        if (beanName != null) {
            builder.addConstructorArgReference(beanName);
        } else {
            builder.addConstructorArgValue(ClassUtil.newInstance(className));
        }
        builder.addConstructorArgValue(this.handlerMethodName());
    }

    protected Class<?> handlerClass() {
        return CustomMessageHandler.class;
    }

    abstract protected String handlerMethodName();
}
