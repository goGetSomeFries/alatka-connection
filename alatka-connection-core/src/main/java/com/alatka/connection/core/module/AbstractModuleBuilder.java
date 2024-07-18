package com.alatka.connection.core.module;

/**
 * @author ybliu
 */
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    private String identity;

    public AbstractModuleBuilder(String identity) {
        this.identity = identity;
    }

    @Override
    public final void build(T model) {
        S convert = this.convert(model);
        this.doBuild(convert);
    }

    protected S convert(T model) {
        return (S) model;
    }

    protected abstract void doBuild(S model);
}
