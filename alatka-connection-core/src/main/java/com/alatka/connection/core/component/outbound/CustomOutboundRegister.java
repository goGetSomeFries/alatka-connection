package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.handler.CustomHandlerRegister;
import com.alatka.connection.core.property.core.CustomHandlerProperty;
import com.alatka.connection.core.property.core.CustomOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 */
public class CustomOutboundRegister extends OutboundComponentRegister<CustomOutboundProperty> {

    private final CustomHandlerRegister customHandlerRegister = new CustomHandlerRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, CustomOutboundProperty property) {
        CustomHandlerProperty handler = new CustomHandlerProperty();
        handler.setExpression(property.getExpression());
        handler.setBeanName(property.getBeanName());
        handler.setClassName(property.getClassName());
        handler.setId(property.getId());
        handler.setOrder(property.getOrder());
        handler.setOutputChannel(property.getOutputChannel());
        this.customHandlerRegister.doRegister(builder, handler);
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(CustomOutboundProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<CustomOutboundProperty> mappingKey() {
        return CustomOutboundProperty.class;
    }

}
