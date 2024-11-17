package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * alatka.connection.route.outbound.redirect
 *
 * @author ybliu
 */
public class RedirectOutboundProperty extends OutboundProperty {

    @IdentityProperty
    private String redirectChannel;

    public String getRedirectChannel() {
        return redirectChannel;
    }

    public void setRedirectChannel(String redirectChannel) {
        this.redirectChannel = redirectChannel;
    }
}
