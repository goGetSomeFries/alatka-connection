package com.alatka.connection.core.model;

import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.http.HttpOutboundProperty;
import com.alatka.connection.core.property.test.MockerOutboundProperty;

/**
 * @author ybliu
 */
public enum OutboundModel {

    mocker(MockerOutboundProperty.class, true),
    http(HttpOutboundProperty.class, true);

    /**
     * {@link ChannelAdapterProperty} {@link Class} 类型
     */
    private Class<? extends ChannelAdapterProperty> type;
    /**
     * 双向通信
     */
    private boolean duplex;

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