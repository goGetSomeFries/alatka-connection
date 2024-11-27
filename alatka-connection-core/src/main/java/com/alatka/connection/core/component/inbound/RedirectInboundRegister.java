package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.component.consumer.ConsumerEndpointRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.core.ConsumerProperty;
import com.alatka.connection.core.property.core.RedirectInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class RedirectInboundRegister extends InboundComponentRegister<RedirectInboundProperty> {

    private ConsumerEndpointRegister register = new ConsumerEndpointRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, RedirectInboundProperty property) {
        ConsumerProperty consumerProperty = new ConsumerProperty();
        consumerProperty.setInputChannel(property.getRedirectChannel());
        String beanName = property.getId().concat(".").concat(this.beanNameSuffix());
        consumerProperty.setMessageHandler(beanName);
        consumerProperty.setId(beanName.concat(".consumer"));
        this.register.register(consumerProperty);
    }

    @Override
    protected Class<BridgeHandler> componentClass() {
        return BridgeHandler.class;
    }

    @Override
    public Class<RedirectInboundProperty> mappingKey() {
        return RedirectInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.redirect.name();
    }
}
