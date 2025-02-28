package com.alatka.connection.test.component.outbound;

import com.alatka.connection.core.component.handler.MessageProcessorRegister;
import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.core.MessageProcessorProperty;
import com.alatka.connection.core.property.test.MockerOutboundProperty;
import com.alatka.connection.test.support.OutboundMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;
import org.springframework.messaging.Message;

/**
 * 模数据拟组件注册器
 *
 * @author ybliu
 * @see OutboundMocker#mockOutbound(Message)
 * @see com.alatka.connection.core.model.OutboundModel#mocker
 */
public class MockerOutboundRegister extends OutboundComponentRegister<MockerOutboundProperty> {

    private final InnerMockerOutboundRegister register = new InnerMockerOutboundRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerOutboundProperty property) {
        this.register.doRegister(builder, property.getCustom());
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(MockerOutboundProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<MockerOutboundProperty> mappingKey() {
        return MockerOutboundProperty.class;
    }

    private static class InnerMockerOutboundRegister extends MessageProcessorRegister<MessageProcessorProperty> {

        @Override
        protected Class<ServiceActivatorFactoryBean> componentClass(MessageProcessorProperty property) {
            return ServiceActivatorFactoryBean.class;
        }

        @Override
        public Class<MessageProcessorProperty> mappingKey() {
            return null;
        }

        @Override
        @SuppressWarnings("rawtypes")
        protected Class<OutboundMocker> handlerClass() {
            return OutboundMocker.class;
        }

        @Override
        protected String handlerMethodName() {
            return OutboundMocker.METHOD_NAME;
        }
    }
}
