package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.handler.SubflowHandlerRegister;
import com.alatka.connection.core.property.core.SubflowHandlerProperty;
import com.alatka.connection.core.property.core.SubflowOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.gateway.GatewayMessageHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class SubflowOutboundRegister extends OutboundComponentRegister<SubflowOutboundProperty> {

    private final SubflowHandlerRegister handlerRegister = new SubflowHandlerRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, SubflowOutboundProperty property) {
        SubflowHandlerProperty handler = new SubflowHandlerProperty();
        handler.setIdentity(property.getIdentity());
        handler.setRequestChannel(property.getRequestChannel());
        handler.setReplyChannel(property.getReplyChannel());
        handler.setErrorChannel(property.getErrorChannel());
        handler.setRequestTimeout(property.getRequestTimeout());
        handler.setReplyTimeout(property.getReplyTimeout());
        handler.setId(property.getId());
        handler.setOrder(property.getOrder());
        handler.setOutputChannel(property.getOutputChannel());
        this.handlerRegister.doRegister(builder, handler);
    }

    @Override
    protected Class<GatewayMessageHandler> componentClass(SubflowOutboundProperty property) {
        return GatewayMessageHandler.class;
    }

    @Override
    public Class<SubflowOutboundProperty> mappingKey() {
        return SubflowOutboundProperty.class;
    }

}
