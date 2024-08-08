package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * {@link ComponentRegister}抽象类
 *
 * @author ybliu
 */
public abstract class AbstractComponentRegister<T extends Property, S> implements ComponentRegister<T, S> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static DefaultListableBeanFactory beanFactory;

    protected AbstractComponentRegister() {
        initialize();
    }

    @Override
    public final String register(T property, String beanNamePrefix, boolean custom) {
        Validator.validateByException(property);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.beanClass());
        this.preDoRegister(builder, property);
        this.doRegister(builder, property);
        this.postDoRegister(builder, property);

        String beanName = this.resolveBeanName(beanNamePrefix, custom);
        beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    public static void init(DefaultListableBeanFactory beanFactory) {
        AbstractComponentRegister.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 组件加载初始化
     */
    protected void initialize() {
    }

    protected String resolveBeanName(String beanNamePrefix, boolean custom) {
        return custom ? beanNamePrefix :
                beanNamePrefix == null ? beanNameSuffix() : beanNamePrefix.concat(".").concat(beanNameSuffix());
    }

    protected String beanNameSuffix() {
        return this.beanClass().getSimpleName();
    }

    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
    }

    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
    }

    protected abstract void doRegister(BeanDefinitionBuilder builder, T property);

    protected abstract Class<?> beanClass();
}
