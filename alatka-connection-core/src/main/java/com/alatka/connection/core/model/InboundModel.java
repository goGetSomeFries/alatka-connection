package com.alatka.connection.core.model;

import com.alatka.connection.core.property.InboundProperty;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import com.alatka.connection.core.property.socket.TcpSimplexInboundProperty;
import com.alatka.connection.core.property.test.MockerInboundProperty;

/**
 * @author ybliu
 */
public enum InboundModel {

    mocker_duplex(MockerInboundProperty.class, true),
    mocker_simplex(MockerInboundProperty.class, false),
    tcp_duplex(TcpSimplexInboundProperty.class, true),
    tcp_simplex(TcpSimplexInboundProperty.class, false),
    http(HttpInboundProperty.class, true);

    /**
     * {@link InboundProperty} {@link Class} 类型
     */
    private Class<? extends InboundProperty> type;
    /**
     * 双向通信
     */
    private boolean duplex;

    InboundModel(Class<? extends InboundProperty> type, boolean duplex) {
        this.type = type;
        this.duplex = duplex;
    }

    public Class<? extends InboundProperty> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }
}
