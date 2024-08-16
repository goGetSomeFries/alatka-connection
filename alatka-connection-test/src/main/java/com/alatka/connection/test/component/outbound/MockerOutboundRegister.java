package com.alatka.connection.test.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.test.MockerOutboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.test.support.OutboundMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @author ybliu
 * @see OutboundMocker#mockOutbound(Message)
 */
public class MockerOutboundRegister extends OutboundComponentRegister<MockerOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerOutboundProperty property) {
        Object instance = ClassUtil.newInstance(property.getClassName());
        if (!OutboundMocker.class.isAssignableFrom(instance.getClass())) {
            throw new IllegalArgumentException(instance.getClass().getName() + " must be an instance of " + OutboundMocker.class.getName());
        }
        builder.addConstructorArgValue(instance)
                .addConstructorArgValue(OutboundMocker.METHOD_NAME);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public Class<MockerOutboundProperty> mappingKey() {
        return MockerOutboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return OutboundModel.mocker.name();
    }
}
