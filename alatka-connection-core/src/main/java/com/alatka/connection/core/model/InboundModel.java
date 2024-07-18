package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.TcpInboundProperty;
import com.alatka.connection.core.property.test.MockerProperty;

/**
 * @author ybliu
 */
public enum InboundModel {

    mocker(MockerProperty.class, true),
    tcp(TcpInboundProperty.class, true);

    private Class<? extends Property> type;
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
