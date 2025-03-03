package com.alatka.connection.core.property.core;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.inbound.redirect
 *
 * @author ybliu
 */
public class RedirectInboundProperty extends InboundProperty {

    @NotEmpty
    private String identity;

    @NotEmpty
    private String redirectChannel;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getRedirectChannel() {
        return redirectChannel;
    }

    public void setRedirectChannel(String redirectChannel) {
        this.redirectChannel = redirectChannel;
    }

}
