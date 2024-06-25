package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.DefinitionModuleBuilder;
import com.alatka.connection.core.util.YamlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ConnectionInit implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    public static final String BEAN_NAME = ConnectionInit.class.getName();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!(registry instanceof DefaultListableBeanFactory)) {
            throw new IllegalArgumentException("");
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;
        AbstractComponentRegister.init(beanFactory);

        try {
            ClassPathResource resource = new ClassPathResource("connection.yml");
            RootModel model = YamlUtil.getObject(resource.getFile(), "alatka.connection", RootModel.class);
            new DefinitionModuleBuilder().build(model.getDefinition());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}
