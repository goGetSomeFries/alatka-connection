package com.alatka.connection.core.module;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author ybliu
 */
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public final void build(T model) {
        S convert = this.convert(model);
        this.doBuild(convert);
    }

    protected S convert(T model) {
        return (S) model;
    }

    protected abstract void doBuild(S model);

    protected DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
