package com.alatka.connection.core.module;

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
//        return ConnectionConstant.INBOUND_BYPASS_CHANNEL;
        return null;
    }

    @Override
    protected String outputChannel() {
//        return ConnectionConstant.OUTBOUND_BYPASS_CHANNEL;
        return null;
    }

}