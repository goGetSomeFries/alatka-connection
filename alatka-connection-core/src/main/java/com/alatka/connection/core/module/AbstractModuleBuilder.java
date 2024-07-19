package com.alatka.connection.core.module;

import com.alatka.connection.core.util.Validator;

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
        if (model != null) {
            Validator.validateByException(model);
        }
        S convert = this.convert(model);
        this.doBuild(convert);
    }

    protected S convert(T model) {
        return (S) model;
    }

    protected abstract void doBuild(S model);
}
