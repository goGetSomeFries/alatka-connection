package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.property.core.ForwardOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.gateway.GatewayMessageHandler;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @author ybliu
 */
public class ForwardOutboundRegister extends OutboundComponentRegister<ForwardOutboundProperty> {

    private final SubflowOutboundRegister handlerRegister = new SubflowOutboundRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ForwardOutboundProperty property) {
        this.handlerRegister.doRegister(builder, property);
    }

    @Override
    protected Class<CustomGatewayMessageHandler> componentClass(ForwardOutboundProperty property) {
        return CustomGatewayMessageHandler.class;
    }

    @Override
    public Class<ForwardOutboundProperty> mappingKey() {
        return ForwardOutboundProperty.class;
    }

    public static class CustomGatewayMessageHandler extends GatewayMessageHandler {

        @Override
        protected Object handleRequestMessage(Message<?> requestMessage) {
            super.handleRequestMessage(requestMessage);
            return null;
        }
    }

}
