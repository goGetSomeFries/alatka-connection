package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.MessageProcessorProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.AbstractStandardMessageHandlerFactoryBean;

/**
 * @author ybliu
 */
public abstract class MessageProcessorRegister<T extends MessageProcessorProperty> extends HandlerComponentRegister<T> {

    @Override
    public void doRegister(BeanDefinitionBuilder builder, T property) {
        String expression = property.getExpression();
        if (expression != null) {
            builder.addPropertyValue("expressionString", expression);
        } else {
            String beanName = property.getBeanName();
            String className = property.getClassName();
            if (beanName == null && className == null) {
                throw new IllegalArgumentException("beanName and className must not be null both");
            }

            Class<?> clazz = ClassUtil.forName(beanName != null ? getBeanFactory().getBeanDefinition(beanName).getBeanClassName() : className);
            if (!this.handlerClass().isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(clazz.getName() + " must be an instance of " + this.handlerClass().getName());
            }

            if (beanName != null) {
                builder.addPropertyReference("targetObject", beanName);
            } else {
                builder.addPropertyValue("targetObject", this.createObject(property));
            }
            builder.addPropertyValue("targetMethodName", this.handlerMethodName());
        }
    }

    protected Class<?> handlerClass() {
        throw new UnsupportedOperationException(getClass().getName() + " does not support handlerClass().");
    }

    protected String handlerMethodName() {
        throw new UnsupportedOperationException(getClass().getName() + " does not support handlerMethodName().");
    }

    protected Object createObject(T property) {
        return ClassUtil.newInstance(property.getClassName());
    }

    @Override
    protected abstract Class<? extends AbstractStandardMessageHandlerFactoryBean> componentClass(T property);

}
