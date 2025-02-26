package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.socket.TcpSimplexInboundProperty;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;

/**
 * {@link TcpReceivingChannelAdapter}组件注册器
 *
 * @author ybliu
 * @see TcpReceivingChannelAdapter
 */
public class TcpSimplexInboundRegister extends TcpInboundRegister<TcpSimplexInboundProperty> {

    @Override
    protected Class<TcpReceivingChannelAdapter> componentClass(TcpSimplexInboundProperty property) {
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
