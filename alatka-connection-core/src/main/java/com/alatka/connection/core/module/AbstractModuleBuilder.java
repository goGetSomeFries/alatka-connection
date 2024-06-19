package com.alatka.connection.core.module;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ybliu
 */
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public final void build(T model) {
        S convert = this.convert(model);
        this.doBuild(convert);
    }

    protected S convert(T model) {
        return (S) model;
    }

    protected abstract void doBuild(S model);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }
}
