package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.DefinitionModuleBuilder;
import com.alatka.connection.core.module.InboundModuleBuilder;
import com.alatka.connection.core.util.YamlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.config.IntegrationConfigurationInitializer;

import java.io.IOException;

/**
 * @author ybliu
 */
public class AlatkaConnectionInitializer implements IntegrationConfigurationInitializer {

    public static final String BEAN_NAME = AlatkaConnectionInitializer.class.getName();

    @Override
    public void initialize(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AbstractComponentRegister.init((DefaultListableBeanFactory) beanFactory);

        try {
            ClassPathResource resource = new ClassPathResource("connection.yml");
            RootModel model = YamlUtil.getObject(resource.getFile(), "alatka.connection", RootModel.class);

            new DefinitionModuleBuilder().build(model.getDefinition());
            new InboundModuleBuilder().build(model.getRoute().getInbound());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
