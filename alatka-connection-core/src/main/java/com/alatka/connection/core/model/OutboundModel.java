package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.ChannelAdapterProperty;
import com.alatka.connection.core.property.http.HttpOutboundProperty;
import com.alatka.connection.core.property.socket.TcpDuplexOutboundProperty;
import com.alatka.connection.core.property.socket.TcpSimplexOutboundProperty;
import com.alatka.connection.core.property.test.MockerOutboundProperty;

/**
 * @author ybliu
 */
public enum OutboundModel {

    mocker(MockerOutboundProperty.class, true),
    tcp_simplex(TcpSimplexOutboundProperty.class, false),
    tcp_duplex(TcpDuplexOutboundProperty.class, true),
    http(HttpOutboundProperty.class, true);

    /**
     * {@link ChannelAdapterProperty} {@link Class} 类型
     */
    private final Class<? extends ChannelAdapterProperty> type;
    /**
     * 双向通信
     */
    private final boolean duplex;

    OutboundModel(Class<? extends ChannelAdapterProperty> type, boolean duplex) {
        this.type = type;
        this.duplex = duplex;
    }

    public Class<? extends ChannelAdapterProperty> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }
}