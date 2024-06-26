package com.alatka.connection;

import com.alatka.connection.core.AlatkaConnectionInitializer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * 注册{@link AlatkaConnectionInitializer}<br>
 * 保证{@link AlatkaConnectionInitializer}先{@link org.springframework.context.annotation.ConfigurationClassPostProcessor}执行，TODO
 *
 * @author ybliu
 */
@Deprecated
public class ConnectionApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (!(beanFactory instanceof DefaultListableBeanFactory)) {
            throw new IllegalArgumentException("");
        }
        DefaultListableBeanFactory registry = (DefaultListableBeanFactory) beanFactory;

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(AlatkaConnectionInitializer.class, AlatkaConnectionInitializer::new);
        registry.registerBeanDefinition(AlatkaConnectionInitializer.BEAN_NAME, builder.getBeanDefinition());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
