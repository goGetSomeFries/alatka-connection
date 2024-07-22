package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.http.HttpOutboundProperty;

/**
 * @author ybliu
 */
public enum OutboundModel {

    http(HttpOutboundProperty.class, true);

    /**
     * {@link Property} {@link Class} 类型
     */
    private Class<? extends Property> type;
    /**
     * 双向通信
     */
    private boolean duplex;

    OutboundModel(Class<? extends Property> type, boolean duplex) {
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