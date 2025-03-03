package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.handler.CustomHandlerRegister;
import com.alatka.connection.core.property.core.CustomOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * 自定义组件注册器
 *
 * @author ybliu
 * @see CustomHandlerRegister
 * @see com.alatka.connection.core.model.OutboundModel#custom
 */
public class CustomOutboundRegister extends OutboundComponentRegister<CustomOutboundProperty> {

    private final CustomHandlerRegister customHandlerRegister = new CustomHandlerRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, CustomOutboundProperty property) {
        this.customHandlerRegister.doRegister(builder, property.getCustom());
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
