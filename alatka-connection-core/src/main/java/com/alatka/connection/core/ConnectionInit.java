package com.alatka.connection.core;

import com.alatka.connection.core.component.SupportComponentRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class ConnectionInit implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!(registry instanceof DefaultListableBeanFactory)) {
            throw new IllegalArgumentException("");
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;

        SpringFactoriesLoader.loadFactories(SupportComponentRegister.class, null).stream()
                .forEach(componentRegister -> componentRegister.setBeanFactory(beanFactory));

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
