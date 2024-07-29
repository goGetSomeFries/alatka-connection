package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.InboundProperty;

/**
 * alatka.connection.inbound.tcp_simplex
 *
 * @author ybliu
 */
public class TcpSimplexInboundProperty extends InboundProperty {

    private boolean clientMode = false;

    private Long retryInterval;

    private String connectionFactory;

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

    public String getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(String connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
