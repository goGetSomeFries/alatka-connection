package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author ybliu
 */
public abstract class AbstractComponentRegister<T extends Property> implements ComponentRegister<T>, InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private BeanDefinitionRegistry registry;

    @Override
    public final String register(T property, String beanNamePrefix, boolean custom) {
        Validator.validateByException(property);

        BeanDefinitionBuilder builder = this.preRegister();
        this.doRegister(builder, property);
        this.postRegister(builder, property);

        String beanName = this.resolveBeanName(beanNamePrefix, custom);
        if (!property.isEnabled()) {
            this.logger.debug("bean [" + beanName + "] is disabled.");
            return null;
        }
        this.registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    @Override
    public void setBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    protected void init() {
    }

    protected BeanDefinitionBuilder preRegister() {
        return BeanDefinitionBuilder.genericBeanDefinition(this.beanClass());
    }

    protected void postRegister(BeanDefinitionBuilder builder, T property) {
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
