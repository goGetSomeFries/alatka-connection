package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.socket.TcpDuplexInboundProperty;
import org.springframework.integration.ip.tcp.TcpInboundGateway;

/**
 * @author ybliu
 */
public class TcpDuplexInboundRegister extends TcpInboundRegister<TcpDuplexInboundProperty> {

    @Override
    protected Class<TcpInboundGateway> componentClass(TcpDuplexInboundProperty property) {
        return TcpInboundGateway.class;
    }

    @Override
    public Class<TcpDuplexInboundProperty> mappingKey() {
        return TcpDuplexInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.tcp_duplex.name();
    }
}
