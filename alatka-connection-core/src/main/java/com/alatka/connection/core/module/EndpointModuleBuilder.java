package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;

import java.util.Map;

/**
 * inbound/outbound父类
 *
 * @param <T>
 * @param <S>
 * @see InboundModuleBuilder
 * @see OutboundModuleBuilder
 */
public abstract class EndpointModuleBuilder<T, S> extends AbstractModuleBuilder<T, S> {

    private boolean duplex;

    public EndpointModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(S model, Map<Object, ? extends ComponentRegister> mapping) {
        this.buildInputChannel();
        this.buildOutputChannel();
    }

    /**
     * 构建inputChannel
     */
    protected abstract void buildInputChannel();

    /**
     * 构建outputChannel
     */
    protected abstract void buildOutputChannel();

    /**
     * 是否双工
     */
    protected boolean isDuplex() {
        return duplex;
    }

    protected void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }
}
