package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.core.RedirectOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class RedirectOutboundRegister extends OutboundComponentRegister<RedirectOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, RedirectOutboundProperty property) {
        builder.addPropertyValue("outputChannelName", property.getRedirectChannel());
    }

    @Override
    protected Class<BridgeHandler> componentClass() {
        return BridgeHandler.class;
    }

    @Override
    public Class<RedirectOutboundProperty> mappingKey() {
        return RedirectOutboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return OutboundModel.redirect.name();
    }
}
