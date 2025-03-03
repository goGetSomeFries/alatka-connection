package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.socket.TcpDuplexInboundProperty;
import org.springframework.integration.ip.tcp.TcpInboundGateway;

/**
 * {@link TcpInboundGateway}组件注册器
 *
 * @author ybliu
 * @see TcpInboundGateway
 * @see InboundModel#tcp_duplex
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
