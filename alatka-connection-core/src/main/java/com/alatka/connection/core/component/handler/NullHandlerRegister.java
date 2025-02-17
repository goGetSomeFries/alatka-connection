package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.NullHandlerProperty;
import com.alatka.connection.core.support.NullMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 */
public class NullHandlerRegister extends MessageProcessorRegister<NullHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, NullHandlerProperty property) {
        super.preDoRegister(builder, property);
        property.setClassName(NullMessageHandler.class.getName());
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
