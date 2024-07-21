package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.DefinitionModuleBuilder;
import com.alatka.connection.core.module.InboundModuleBuilder;
import com.alatka.connection.core.module.OutboundModuleBuilder;
import com.alatka.connection.core.module.ProcessorModuleBuilder;
import com.alatka.connection.core.util.YamlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.integration.config.IntegrationConfigurationInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public class AlatkaConnectionInitializer implements IntegrationConfigurationInitializer {

    public static final String BEAN_NAME = AlatkaConnectionInitializer.class.getName();

    private static final String FILE_PREFIX = "alatka-connection";

    private static final String FILE_YML_SUFFIX = ".yml";

    private static final String FILE_YAML_SUFFIX = ".yaml";

    @Override
    public void initialize(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AbstractComponentRegister.init((DefaultListableBeanFactory) beanFactory);

        for (Resource resource : this.loadResources()) {
            String identity = this.identity(resource.getFilename());
            RootModel rootModel = this.rootModel(resource);

            // alatka.connection.definition
            DefinitionModuleBuilder definitionModuleBuilder = new DefinitionModuleBuilder(identity);
            definitionModuleBuilder.build(rootModel.getDefinition());

            // alatka.connection.route.processor
            ProcessorModuleBuilder processorModuleBuilder = new ProcessorModuleBuilder(identity);
            processorModuleBuilder.build(rootModel.getRoute().getProcessors());

            // alatka.connection.route.inbound
            InboundModuleBuilder inboundModuleBuilder = new InboundModuleBuilder(identity);
            inboundModuleBuilder.build(rootModel.getRoute().getInbound());

            // alatka.connection.route.outbound
            OutboundModuleBuilder outboundModuleBuilder = new OutboundModuleBuilder(identity);
            outboundModuleBuilder.build(rootModel.getRoute().getOutbound());
        }
    }

    private List<Resource> loadResources() {
        try {
            Resource[] ymlResources = ResourcePatternUtils.getResourcePatternResolver(null)
                    .getResources(FILE_PREFIX + "*" + FILE_YML_SUFFIX);
            Resource[] yamlResources = ResourcePatternUtils.getResourcePatternResolver(null)
                    .getResources(FILE_PREFIX + "*" + FILE_YAML_SUFFIX);

            List<Resource> list = Stream.concat(Stream.of(ymlResources), Stream.of(yamlResources)).collect(Collectors.toList());
            return Collections.unmodifiableList(list);
        } catch (IOException e) {
            throw new IllegalArgumentException("加载" + FILE_PREFIX + "失败", e);
        }
    }

    /**
     * alatka-connection-[identity].yml<br><br>
     * alatka-connection-channel2.yml -> channel2<br>
     * alatka-connection-cpsfe.yaml -> cpsfe<br>
     * alatka-connection-app.yaml -> app<br>
     * alatka-connection.yml -> default<br>
     *
     * @param fileName
     * @return
     */
    private String identity(String fileName) {
        String str = fileName.substring(fileName.indexOf(FILE_PREFIX) + FILE_PREFIX.length(), fileName.lastIndexOf("."));
        return str.isEmpty() ? "default" : str.substring(1);
    }

    private RootModel rootModel(Resource resource) {
        try {
            return YamlUtil.getObject(resource.getFile(), "alatka.connection", RootModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
