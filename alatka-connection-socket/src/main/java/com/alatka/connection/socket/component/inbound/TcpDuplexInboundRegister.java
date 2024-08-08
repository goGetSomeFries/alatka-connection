package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.socket.TcpSimplexInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.TcpInboundGateway;

/**
 * @author ybliu
 */
public class TcpDuplexInboundRegister extends InboundComponentRegister<TcpSimplexInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpSimplexInboundProperty property) {
        builder.addPropertyValue("clientMode", property.isClientMode())
                .addPropertyReference("connectionFactory", property.getConnectionFactory());
        if (property.getRetryInterval() != null) {
            builder.addPropertyValue("retryInterval", property.getRetryInterval());
        }
    }

    @Override
    protected Class<TcpInboundGateway> beanClass() {
        return TcpInboundGateway.class;
    }

    @Override
    public Class<TcpSimplexInboundProperty> mappingKey() {
        return TcpSimplexInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.tcp_duplex.name();
    }
}
