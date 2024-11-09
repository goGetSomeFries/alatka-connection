package com.alatka.connection.test.component.inbound;

import com.alatka.connection.core.component.inbound.SourcePollingInboundRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.test.MockerInboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.test.support.InboundMocker;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;

/**
 * TODO
 *
 * @author ybliu
 * @see InboundMocker#mockInbound()
 */
public class MockerInboundRegister extends SourcePollingInboundRegister<MockerInboundProperty> {

    @Override
    protected MessageSource messageSource(MockerInboundProperty property) {
        Object instance = ClassUtil.newInstance(property.getClassName());
        if (!InboundMocker.class.isAssignableFrom(instance.getClass())) {
            throw new IllegalArgumentException(instance.getClass().getName() + " must be an instance of " + InboundMocker.class.getName());
        }
        MethodInvokingMessageSource messageSource = new MethodInvokingMessageSource();
        messageSource.setObject(instance);
        messageSource.setMethodName(InboundMocker.METHOD_NAME);
        messageSource.setBeanFactory(this.getBeanFactory());
        messageSource.afterPropertiesSet();
        return messageSource;
    }

    @Override
    public Class<MockerInboundProperty> mappingKey() {
        return MockerInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.mocker.name();
    }

}
