package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;

/**
 * @author ybliu
 */
public class BypassModuleBuilder extends OutboundModuleBuilder {

    public BypassModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected String beanNamePrefix() {
        return "bypass";
    }

    @Override
    protected String inputChannel() {
        return ConnectionConstant.INBOUND_BYPASS_CHANNEL;
    }

    @Override
    protected String outputChannel() {
        return ConnectionConstant.OUTBOUND_BYPASS_CHANNEL;
    }

}