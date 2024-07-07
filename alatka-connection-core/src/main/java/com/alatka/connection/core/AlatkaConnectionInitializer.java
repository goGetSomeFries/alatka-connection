package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.DefinitionModuleBuilder;
import com.alatka.connection.core.module.InboundModuleBuilder;
import com.alatka.connection.core.util.YamlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.integration.config.IntegrationConfigurationInitializer;

import java.io.File;
import java.io.IOException;

/**
 * @author ybliu
 */
public class AlatkaConnectionInitializer implements IntegrationConfigurationInitializer {

    public static final String BEAN_NAME = AlatkaConnectionInitializer.class.getName();

    private static final String FILE_PREFIX = "alatka-connection";

    private static final String FILE_SUFFIX = ".yml";

    @Override
    public void initialize(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AbstractComponentRegister.init((DefaultListableBeanFactory) beanFactory);

        try {
            Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(null)
                    .getResources(FILE_PREFIX + "*" + FILE_SUFFIX);
            for (Resource resource : resources) {
                System.out.println(resource);
                File file = resource.getFile();
                RootModel model = YamlUtil.getObject(file, "alatka.connection", RootModel.class);
                new DefinitionModuleBuilder().build(model.getDefinition());
                new InboundModuleBuilder().build(model.getRoute().getInbound());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * alatka-connection-channel2.yml -> channel2<br>
     * alatka-connection-cpsfe.yml -> cpsfe<br>
     * alatka-connection-app.yml -> app<br>
     * alatka-connection.yml -> default<br>
     *
     * @param fileName
     * @return
     */
    private String identity(String fileName) {
        String str = fileName.substring(fileName.indexOf(FILE_PREFIX) + FILE_PREFIX.length(), fileName.indexOf(FILE_SUFFIX));
        return str.isEmpty() ? "default" : str.substring(1);
    }

}
