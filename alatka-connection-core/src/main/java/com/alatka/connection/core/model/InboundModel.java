package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.TcpInboundProperty;
import com.alatka.connection.core.property.test.MockerProperty;

/**
 * @author ybliu
 */
public enum InboundModel {

    mocker_duplex(MockerProperty.class, true),
    mocker_simplex(MockerProperty.class, false),
    tcp_duplex(TcpInboundProperty.class, true),
    http(TcpInboundProperty.class, true);

    /**
     * {@link Property} {@link Class} 类型
     */
    private Class<? extends Property> type;
    /**
     * 双向通信
     */
    private boolean duplex;

    InboundModel(Class<? extends Property> type, boolean duplex) {
        this.type = type;
        this.duplex = duplex;
    }

    public Class<? extends Property> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }
}
