package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.annotation.IdentityPropertyReference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * alatka.connection.route.outbound.router
 *
 * @author ybliu
 */
public class RouterOutboundProperty extends OutboundProperty {

    @NotEmpty
    @IdentityPropertyReference
    private List<RouterProperty> items;

    @IdentityProperty
    private String defaultChannel;

    public static class RouterProperty {

        @NotBlank
        @IdentityProperty
        private String channel;

        private boolean channelExisted;

        private String expression;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public boolean isChannelExisted() {
            return channelExisted;
        }

        public void setChannelExisted(boolean channelExisted) {
            this.channelExisted = channelExisted;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }

    public List<RouterProperty> getItems() {
        return items;
    }

    public void setItems(List<RouterProperty> items) {
        this.items = items;
    }

    public String getDefaultChannel() {
        return defaultChannel;
    }

    public void setDefaultChannel(String defaultChannel) {
        this.defaultChannel = defaultChannel;
    }
}