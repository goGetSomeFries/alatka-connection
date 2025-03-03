package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.LoggerHandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.core.support.LoggerMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * 日志组件注册器
 *
 * @author ybliu
 * @see LoggerMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#logger
 */
public class LoggerHandlerRegister extends MessageProcessorRegister<LoggerHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, LoggerHandlerProperty property) {
        super.preDoRegister(builder, property);
        property.setClassName(LoggerMessageHandler.class.getName());
    }

    @Override
    protected Class<LoggerMessageHandler> handlerClass() {
        return LoggerMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return CustomMessageHandler.METHOD_NAME;
    }

    @Override
    protected Object createObject(LoggerHandlerProperty property) {
        LoggerMessageHandler handler = new LoggerMessageHandler();
        if (property.getLoggerName() != null) {
            handler.setLoggerName(property.getLoggerName());
        }
        if (property.getLevel() != null) {
            handler.setLevel(property.getLevel());
        }
        if (property.getLogExpression() != null) {
            handler.setExpression(property.getLogExpression());
        }
        return handler;
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(LoggerHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<LoggerHandlerProperty> mappingKey() {
        return LoggerHandlerProperty.class;
    }
}
