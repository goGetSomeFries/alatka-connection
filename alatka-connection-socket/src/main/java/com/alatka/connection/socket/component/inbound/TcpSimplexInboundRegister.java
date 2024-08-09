package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.socket.TcpSimplexInboundProperty;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;

/**
 * @author ybliu
 */
public class TcpSimplexInboundRegister extends TcpInboundRegister {

    @Override
    protected Class<TcpReceivingChannelAdapter> componentClass() {
        return TcpReceivingChannelAdapter.class;
    }

    @Override
    public Class<TcpSimplexInboundProperty> mappingKey() {
        return TcpSimplexInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.tcp_simplex.name();
    }
}
