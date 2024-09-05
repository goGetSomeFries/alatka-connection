package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

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
