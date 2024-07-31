package com.alatka.connection.core.module;

/**
 * @author ybliu
 */
public abstract class BeanNameReferenceModuleBuilder<T, S> extends AbstractModuleBuilder<T, S> {

    private String beanName;

    public BeanNameReferenceModuleBuilder(String identity) {
        super(identity);
    }

    public String getBeanName() {
        return beanName;
    }

    protected void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
