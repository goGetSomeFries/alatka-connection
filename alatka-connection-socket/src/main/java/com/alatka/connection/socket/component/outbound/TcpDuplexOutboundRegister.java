package com.alatka.connection.socket.component.outbound;

import com.alatka.connection.core.property.socket.TcpDuplexOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;

public class TcpDuplexOutboundRegister extends TcpOutboundRegister<TcpDuplexOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpDuplexOutboundProperty property) {
        builder.addPropertyValue("closeStreamAfterSend", property.isCloseStreamAfterSend());
    }

    @Override
    protected Class<TcpOutboundGateway> componentClass() {
        return TcpOutboundGateway.class;
    }

    @Override
    public Class<TcpDuplexOutboundProperty> mappingKey() {
        return TcpDuplexOutboundProperty.class;
    }

}
