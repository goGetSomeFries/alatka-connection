package com.alatka.connection.metric.component.handler;

import com.alatka.connection.core.component.handler.MessageProcessorRegister;
import com.alatka.connection.core.property.metric.TracerHandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.metric.support.TracerMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * 消息跟踪组件注册器
 *
 * @author ybliu
 * @see TracerMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#tracer
 */
public class TracerHandlerRegister extends MessageProcessorRegister<TracerHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, TracerHandlerProperty property) {
        super.preDoRegister(builder, property);
        property.setClassName(TracerMessageHandler.class.getName());
    }

    @Override
    protected Class<TracerMessageHandler> handlerClass() {
        return TracerMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return CustomMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(TracerHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<TracerHandlerProperty> mappingKey() {
        return TracerHandlerProperty.class;
    }
}
