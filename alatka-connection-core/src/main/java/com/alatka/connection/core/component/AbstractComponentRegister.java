package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author ybliu
 */
public abstract class AbstractComponentRegister<T extends Property> implements ComponentRegister<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private DefaultListableBeanFactory beanFactory;

    protected AbstractComponentRegister() {
        init();
    }

    @Override
    public final String register(T property, String beanNamePrefix, boolean custom) {
        Validator.validateByException(property);

        BeanDefinitionBuilder builder = this.preDoRegister();
        this.doRegister(builder, property);
        this.postDoRegister(builder, property);

        String beanName = this.resolveBeanName(beanNamePrefix, custom);
        if (!property.isEnabled()) {
            this.logger.debug("bean [" + beanName + "] is disabled.");
            return null;
        }
        this.beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    @Override
    public void setBeanFactory(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected void init() {
    }

    protected BeanDefinitionBuilder preDoRegister() {
        return BeanDefinitionBuilder.genericBeanDefinition(this.beanClass());
    }

    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
    }

    protected String resolveBeanName(String beanNamePrefix, boolean custom) {
        return custom ? beanNamePrefix :
                beanNamePrefix == null ? beanNameSuffix() : beanNamePrefix.concat(".").concat(beanNameSuffix());
    }

    protected String beanNameSuffix() {
        return this.beanClass().getSimpleName();
    }

    protected abstract void doRegister(BeanDefinitionBuilder builder, T property);

    protected abstract Class<?> beanClass();
}
