package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.TcpInboundProperty;

/**
 * @author ybliu
 */
public enum InboundModel {


    tcp(TcpInboundProperty.class);

    private Class<? extends Property> type;

    InboundModel(Class<? extends Property> type) {
        this.type = type;
    }

    public Class<? extends Property> getType() {
        return type;
    }
}
