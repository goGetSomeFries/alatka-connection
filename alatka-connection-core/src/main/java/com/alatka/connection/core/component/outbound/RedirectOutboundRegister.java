package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.property.core.RedirectOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

/**
 * {@link BridgeHandler}组件注册器
 *
 * @author ybliu
 * @see BridgeHandler
 * @see com.alatka.connection.core.model.OutboundModel#redirect
 */
public class RedirectOutboundRegister extends OutboundComponentRegister<RedirectOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, RedirectOutboundProperty property) {
        String channelName = property.getIdentity() + AlatkaConnectionConstant.IDENTITY_SEPARATOR + property.getRedirectChannel();
        builder.addPropertyValue("outputChannelName", channelName);
    }

    @Override
    protected Class<BridgeHandler> componentClass(RedirectOutboundProperty property) {
        return BridgeHandler.class;
    }

    @Override
    public Class<RedirectOutboundProperty> mappingKey() {
        return RedirectOutboundProperty.class;
    }

}
