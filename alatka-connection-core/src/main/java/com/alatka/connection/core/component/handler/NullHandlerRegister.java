package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.NullMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

import java.util.Map;

/**
 * TODO
 *
 * @author ybliu
 */
public class NullHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        super.preDoRegister(builder, property);
        Map<String, Object> params = property.getParams();
        params.put(KEY_CLASS_NAME, NullMessageHandler.class.getName());
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass() {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.null_;
    }
}
