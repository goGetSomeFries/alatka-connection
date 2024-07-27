package com.alatka.connection.core.module;

public abstract class EndpointModuleBuilder<T, S> extends AbstractModuleBuilder<T, S> {

    private boolean duplex;

    public EndpointModuleBuilder(String identity) {
        super(identity);
    }

    public boolean isDuplex() {
        return duplex;
    }

    public void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }
}
