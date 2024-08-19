package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.annotation.IdentityPropertyReference;
import com.alatka.connection.core.property.core.OutboundProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author ybliu
 */
public class TcpOutboundProperty extends OutboundProperty {

    /**
     * flat convert
     */
    @IdentityPropertyReference
    @JsonUnwrapped
    private TcpConnectionProperty connectionFactory;

    public TcpConnectionProperty getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(TcpConnectionProperty connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
