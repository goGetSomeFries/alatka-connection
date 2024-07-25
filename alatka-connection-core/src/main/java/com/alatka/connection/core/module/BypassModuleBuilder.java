package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;

/**
 * @author ybliu
 */
public class BypassModuleBuilder extends OutboundModuleBuilder {

    public BypassModuleBuilder(String identity) {
        super(identity);
    }

    protected String prefix() {
        return "bypass";
    }

    protected String inputChannel() {
        return ConnectionConstant.INBOUND_BYPASS_CHANNEL;
    }

    protected String outputChannel() {
        return ConnectionConstant.OUTBOUND_BYPASS_CHANNEL;
    }

}