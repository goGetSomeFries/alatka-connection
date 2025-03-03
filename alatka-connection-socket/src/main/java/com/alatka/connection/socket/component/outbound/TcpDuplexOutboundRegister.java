package com.alatka.connection.socket.component.outbound;

import com.alatka.connection.core.property.socket.TcpDuplexOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;

/**
 * {@link TcpOutboundGateway}组件注册器
 *
 * @author whocares
 * @see TcpOutboundGateway
 * @see com.alatka.connection.core.model.OutboundModel#tcp_duplex
 */
public class TcpDuplexOutboundRegister extends TcpOutboundRegister<TcpDuplexOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpDuplexOutboundProperty property) {
        builder.addPropertyValue("closeStreamAfterSend", property.isCloseStreamAfterSend());
    }

    @Override
    protected Class<TcpOutboundGateway> componentClass(TcpDuplexOutboundProperty property) {
        return TcpOutboundGateway.class;
    }

    @Override
    public Class<TcpDuplexOutboundProperty> mappingKey() {
        return TcpDuplexOutboundProperty.class;
    }

}
