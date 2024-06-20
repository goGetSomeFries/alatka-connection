package com.alatka.connection;

import com.alatka.connection.core.component.SupportComponentRegister;
import com.alatka.connection.core.component.support.TaskExecutorRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Map;

public class Init implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println(registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory);
        TaskExecutorRegister bean = beanFactory.getBean("taskExecutorRegister", TaskExecutorRegister.class);
        Map<String, SupportComponentRegister> beansOfType = beanFactory.getBeansOfType(SupportComponentRegister.class);
        System.out.println(beansOfType);
        System.out.println(bean);
    }
}
