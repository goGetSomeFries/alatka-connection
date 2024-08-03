package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.*;
import com.alatka.connection.core.property.ProcessorProperty;
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
 * 初始化入口
 *
 * @author ybliu
 */
public class AlatkaConnectionInitializer implements IntegrationConfigurationInitializer {

    private static final String FILE_PREFIX = "alatka-connection";

    private static final String FILE_YML_SUFFIX = ".yml";

    private static final String FILE_YAML_SUFFIX = ".yaml";

    @Override
    public void initialize(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // init ComponentRegister
        AbstractComponentRegister.init((DefaultListableBeanFactory) beanFactory);

        for (Resource resource : this.loadResources()) {
            String identity = this.getIdentity(resource);
            RootModel rootModel = this.getRootModel(resource);

            // alatka.connection.definition
            DefinitionModuleBuilder definitionModuleBuilder = new DefinitionModuleBuilder(identity);
            definitionModuleBuilder.build(rootModel.getDefinition());

            // alatka.connection.route.inbound
            InboundModuleBuilder inboundModuleBuilder = new InboundModuleBuilder(identity);
            inboundModuleBuilder.build(rootModel.getRoute().getInbound());

            // alatka.connection.route.outbound
            OutboundModuleBuilder outboundModuleBuilder = new OutboundModuleBuilder(identity);
            outboundModuleBuilder.build(rootModel.getRoute().getOutbound());

            // alatka.connection.route.bypass
            BypassModuleBuilder bypassModuleBuilder = new BypassModuleBuilder(identity);
            bypassModuleBuilder.build(rootModel.getRoute().getBypass());

            // alatka.connection.route.processor request
            ProcessorModuleBuilder requestProcessorModuleBuilder = new ProcessorModuleBuilder(identity, ProcessorProperty.Type.request);
            requestProcessorModuleBuilder.build(rootModel.getRoute().getProcessors());

            // alatka.connection.route.processor reply
            ProcessorModuleBuilder replyProcessorModuleBuilder = new ProcessorModuleBuilder(identity, ProcessorProperty.Type.reply);
            replyProcessorModuleBuilder.build(rootModel.getRoute().getProcessors());
        }
    }

    /**
     * 加载alatka-connection*.[yml|yaml]
     *
     * @return {@link Resource}集合
     */
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
     * 获取配置文件唯一标识identity<br>
     * alatka-connection-[identity].yml<br><br>
     * alatka-connection-channel2.yml -> channel2<br>
     * alatka-connection-cpsfe.yaml -> cpsfe<br>
     * alatka-connection-app.yaml -> app<br>
     * alatka-connection.yml -> default<br>
     *
     * @param resource yaml {@link Resource}文件
     * @return [identity]
     */
    private String getIdentity(Resource resource) {
        String fileName = resource.getFilename();
        String str = fileName.substring(fileName.indexOf(FILE_PREFIX) + FILE_PREFIX.length(), fileName.lastIndexOf("."));
        return str.isEmpty() ? "default" : str.substring(1);
    }

    /**
     * 获取yaml文件内容，转换为{@link RootModel}对象
     *
     * @param resource yaml {@link Resource}
     * @return {@link RootModel}
     */
    private RootModel getRootModel(Resource resource) {
        try {
            return YamlUtil.getObject(resource.getFile(), "alatka.connection", RootModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
