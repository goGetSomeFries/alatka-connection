package com.alatka.connection.core.model;

import com.alatka.connection.core.property.InboundProperty;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import com.alatka.connection.core.property.socket.TcpInboundProperty;
import com.alatka.connection.core.property.test.MockerProperty;

/**
 * @author ybliu
 */
public enum InboundModel {

    mocker_duplex(MockerProperty.class, true),
    mocker_simplex(MockerProperty.class, false),
    tcp_duplex(TcpInboundProperty.class, true),
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
