package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.NullHandlerProperty;
import com.alatka.connection.core.support.NullMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * Null组件注册器
 *
 * @author ybliu
 * @see NullMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#null_
 */
public class NullHandlerRegister extends MessageProcessorRegister<NullHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, NullHandlerProperty property) {
        super.preDoRegister(builder, property);
        property.setClassName(NullMessageHandler.class.getName());
    }

    @Override
    protected Class<NullMessageHandler> handlerClass() {
        return NullMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return NullMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(NullHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<NullHandlerProperty> mappingKey() {
        return NullHandlerProperty.class;
    }
}
