package com.alatka.connection.socket.component.outbound;

import com.alatka.connection.core.property.socket.TcpSimplexOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;

public class TcpSimplexOutboundRegister extends TcpOutboundRegister<TcpSimplexOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpSimplexOutboundProperty property) {
        builder.addPropertyValue("clientMode", property.isClientMode());
        if (property.getRetryInterval() != null) {
            builder.addPropertyValue("retryInterval", property.getRetryInterval());
        }
    }

    @Override
    protected Class<TcpSendingMessageHandler> componentClass() {
        return TcpSendingMessageHandler.class;
    }

    @Override
    public Class<TcpSimplexOutboundProperty> mappingKey() {
        return TcpSimplexOutboundProperty.class;
    }

}
