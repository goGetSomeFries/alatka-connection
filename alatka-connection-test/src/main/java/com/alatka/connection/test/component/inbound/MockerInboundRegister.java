package com.alatka.connection.test.component.inbound;

import com.alatka.connection.core.component.inbound.SourcePollingInboundRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.test.MockerInboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.test.support.InboundMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.endpoint.MessageProcessorMessageSource;

/**
 * 模拟数据组件注册器
 *
 * @author ybliu
 * @see InboundMocker#mockInbound()
 * @see InboundModel#mocker
 */
public class MockerInboundRegister extends SourcePollingInboundRegister<MockerInboundProperty> {

    @Override
    protected void doRegisterMessageSource(BeanDefinitionBuilder builder, MockerInboundProperty property) {
        String beanName = property.getBeanName();
        String className = property.getClassName();
        if (beanName == null && className == null) {
            throw new IllegalArgumentException("beanName and className must not be null both");
        }

        Class<?> clazz = ClassUtil.forName(beanName != null ? getBeanFactory().getBeanDefinition(beanName).getBeanClassName() : className);
        if (!InboundMocker.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " must be an instance of " + InboundMocker.class.getName());
        }

        if (beanName != null) {
            builder.addConstructorArgReference(beanName);
        } else {
            builder.addConstructorArgValue(ClassUtil.newInstance(property.getClassName()));
        }
    }

    @Override
    protected Class<MessageProcessorMessageSource> messageSourceClass() {
        return MessageProcessorMessageSource.class;
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
