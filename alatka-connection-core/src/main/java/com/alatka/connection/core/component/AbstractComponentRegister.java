package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author ybliu
 */
public abstract class AbstractComponentRegister<T extends Property> implements ComponentRegister<T>, BeanFactoryAware, InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private BeanDefinitionRegistry registry;

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
        this.registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof DefaultListableBeanFactory)) {
            throw new IllegalArgumentException("beanFactory is not subclass of DefaultListableBeanFactory");
        }
        this.registry = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.registry == null) {
            throw new IllegalArgumentException("BeanDefinitionRegistry must not be null");
        }
        this.init();
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
