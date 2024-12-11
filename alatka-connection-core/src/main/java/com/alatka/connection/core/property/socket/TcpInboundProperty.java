package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.annotation.IdentityPropertyReference;
import com.alatka.connection.core.property.core.InboundProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author ybliu
 */
public abstract class TcpInboundProperty extends InboundProperty {

    private boolean clientMode = false;

    private Long retryInterval;

    /**
     * flat convert
     */
    @IdentityPropertyReference
    @JsonUnwrapped
    private TcpConnectionProperty connectionFactory;

    public boolean isClientMode() {
        return clientMode;
    }

    public void setClientMode(boolean clientMode) {
        this.clientMode = clientMode;
    }

    public Long getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Long retryInterval) {
        this.retryInterval = retryInterval;
    }

    public TcpConnectionProperty getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(TcpConnectionProperty connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
