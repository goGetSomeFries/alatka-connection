package com.alatka.connection.test.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.test.MockerOutboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.test.support.MessageMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * @author ybliu
 * @see MessageMocker#outbound(String)
 */
public class MockerOutboundRegister extends OutboundComponentRegister<MockerOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerOutboundProperty property) {
        Object instance = ClassUtil.newInstance(property.getClassName());
        builder.addConstructorArgValue(instance)
                .addConstructorArgValue(property.getMethodName());
    }

    @Override
    protected Class<ServiceActivatingHandler> beanClass() {
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
