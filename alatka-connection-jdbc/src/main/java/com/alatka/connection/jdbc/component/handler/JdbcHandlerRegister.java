package com.alatka.connection.jdbc.component.handler;

import com.alatka.connection.core.component.handler.MessageProcessorRegister;
import com.alatka.connection.core.property.jdbc.JdbcHandlerProperty;
import com.alatka.connection.jdbc.component.other.JdbcMessageProcessorRegister;
import com.alatka.connection.jdbc.support.JdbcMessageProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 * @see com.alatka.connection.core.model.HandlerModel#jdbc
 */
public class JdbcHandlerRegister extends MessageProcessorRegister<JdbcHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, JdbcHandlerProperty property) {
        super.preDoRegister(builder, property);
        JdbcMessageProcessorRegister embeddedRegister = new JdbcMessageProcessorRegister();
        String beanName = embeddedRegister.register(property);
        property.setBeanName(beanName);
    }

    @Override
    protected Class<JdbcMessageProcessor> handlerClass() {
        return JdbcMessageProcessor.class;
    }

    @Override
    protected String handlerMethodName() {
        return JdbcMessageProcessor.METHOD_NAME;
    }


    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(JdbcHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<JdbcHandlerProperty> mappingKey() {
        return JdbcHandlerProperty.class;
    }
}
