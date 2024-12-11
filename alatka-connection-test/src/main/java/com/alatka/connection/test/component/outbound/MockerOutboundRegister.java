package com.alatka.connection.test.component.outbound;

import com.alatka.connection.core.component.handler.MessageProcessorRegister;
import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.core.CustomHandlerProperty;
import com.alatka.connection.core.property.core.MessageProcessorProperty;
import com.alatka.connection.core.property.test.MockerOutboundProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.test.support.OutboundMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @author ybliu
 * @see OutboundMocker#mockOutbound(Message)
 */
public class MockerOutboundRegister extends OutboundComponentRegister<MockerOutboundProperty> {

    private final InnerMockerOutboundRegister inner = new InnerMockerOutboundRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerOutboundProperty property) {
        CustomHandlerProperty handler = new CustomHandlerProperty();
        handler.setExpression(property.getExpression());
        handler.setBeanName(property.getBeanName());
        handler.setClassName(property.getClassName());
        handler.setId(property.getId());
        handler.setOrder(property.getOrder());
        handler.setOutputChannel(property.getOutputChannel());
        this.inner.doRegister(builder, handler);
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass() {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<MockerOutboundProperty> mappingKey() {
        return MockerOutboundProperty.class;
    }

    private class InnerMockerOutboundRegister extends MessageProcessorRegister<MessageProcessorProperty> {

        @Override
        protected Class<ServiceActivatorFactoryBean> componentClass() {
            return null;
        }

        @Override
        public Class<MessageProcessorProperty> mappingKey() {
            return null;
        }

        @Override
        protected Class<OutboundMocker> handlerClass() {
            return OutboundMocker.class;
        }

        @Override
        protected String handlerMethodName() {
            return CustomMessageHandler.METHOD_NAME;
        }
    }
}
