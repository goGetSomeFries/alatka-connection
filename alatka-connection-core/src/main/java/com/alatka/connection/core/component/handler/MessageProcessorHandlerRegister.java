package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.AbstractStandardMessageHandlerFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class MessageProcessorHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_EXPRESSION = "expression";

    protected static final String KEY_CLASS_NAME = "className";

    private static final String KEY_BEAN_NAME = "beanName";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        String expression = this.getParamsValue(property.getParams(), KEY_EXPRESSION);
        if (expression != null) {
            builder.addPropertyValue("expression", expression);
        } else {
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
                builder.addPropertyReference("targetObject", beanName);
            } else {
                builder.addPropertyValue("targetObject", ClassUtil.newInstance(className));
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

    @Override
    protected abstract Class<? extends AbstractStandardMessageHandlerFactoryBean> componentClass();

}
