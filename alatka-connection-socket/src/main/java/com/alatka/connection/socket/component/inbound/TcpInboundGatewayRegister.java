package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.component.inbound.GatewayRegister;
import com.alatka.connection.core.property.TcpInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.TcpInboundGateway;

/**
 * @author ybliu
 */
public class TcpInboundGatewayRegister extends GatewayRegister<TcpInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpInboundProperty property) {
        builder.addPropertyValue("clientMode", property.isClientMode())
                .addPropertyReference("connectionFactory", property.getSource());
        if (property.getRetryInterval() != null) {
            builder.addPropertyValue("retryInterval", property.getRetryInterval());
        }
    }

    @Override
    protected Class<TcpInboundGateway> beanClass() {
        return TcpInboundGateway.class;
    }
}
