package com.alatka.connection.core.component;

import com.alatka.connection.core.property.core.Property;
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

    public static void init(DefaultListableBeanFactory beanFactory) {
        AbstractComponentRegister.beanFactory = beanFactory;
    }

    @Override
    public final String register(T property) {
        Validator.validateByException(property);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.componentClass());
        this.preDoRegister(builder, property);
        this.doRegister(builder, property);
        this.postDoRegister(builder, property);

        String beanName = beanNameSuffix() == null ?
                property.getId() : property.getId().concat(".").concat(beanNameSuffix());
        beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    protected DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 组件加载初始化
     */
    protected void initialize() {
    }

    protected String beanNameSuffix() {
        return null;
    }

    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
    }

    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
    }

    protected abstract void doRegister(BeanDefinitionBuilder builder, T property);

    protected abstract Class<?> componentClass();
}
