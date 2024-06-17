package com.alatka.connection.core.property;

import com.alatka.connection.core.model.InboundModel;

/**
 * @author ybliu
 */
public class TcpInboundProperty extends ChannelAdapterProperty implements InboundModel {

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
