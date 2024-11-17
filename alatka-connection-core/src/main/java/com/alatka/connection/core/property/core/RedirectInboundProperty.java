package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * alatka.connection.route.inbound.redirect
 *
 * @author ybliu
 */
public class RedirectInboundProperty extends InboundProperty {

    @IdentityProperty
    private String redirectChannel;

    public String getRedirectChannel() {
        return redirectChannel;
    }

    public void setRedirectChannel(String redirectChannel) {
        this.redirectChannel = redirectChannel;
    }
}
