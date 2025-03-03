package com.alatka.connection.core.property.socket;

/**
 * alatka.connection.flow.outbound.tcp_simplex
 *
 * @author ybliu
 */
public class TcpSimplexOutboundProperty extends TcpOutboundProperty {

    private boolean clientMode = true;

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
