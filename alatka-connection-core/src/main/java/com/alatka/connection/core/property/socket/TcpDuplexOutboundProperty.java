package com.alatka.connection.core.property.socket;

/**
 * alatka.connection.flow.outbound.tcp_duplex
 *
 * @author ybliu
 */
public class TcpDuplexOutboundProperty extends TcpOutboundProperty {

    private boolean closeStreamAfterSend;

    public boolean isCloseStreamAfterSend() {
        return closeStreamAfterSend;
    }

    public void setCloseStreamAfterSend(boolean closeStreamAfterSend) {
        this.closeStreamAfterSend = closeStreamAfterSend;
    }
}
