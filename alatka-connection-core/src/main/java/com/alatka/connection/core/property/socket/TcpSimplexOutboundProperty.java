package com.alatka.connection.core.property.socket;

/**
 * alatka.connection.outbound.tcp_simplex
 *
 * @author ybliu
 */
public class TcpSimplexOutboundProperty extends TcpOutboundProperty {

    private boolean clientMode = false;

    private Long retryInterval;


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

}
