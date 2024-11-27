package com.alatka.connection.core;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.*;
import com.alatka.connection.core.property.core.ProcessorProperty;
import com.alatka.connection.core.util.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 初始化入口<br><br>
 * 加载{@link #FILE_PREFIX}配置文件，解析注册为Spring Bean
 *
 * @author ybliu
 */
public class AlatkaConnectionInitializer implements BeanFactoryPostProcessor, Ordered, EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(AlatkaConnectionInitializer.class);

    private static final String FILE_PREFIX = "alatka-connection";

    private static final String[] FILE_SUFFIX = new String[]{".yml", ".yaml"};

    private String classpath;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // init ComponentRegister
        AbstractComponentRegister.init((DefaultListableBeanFactory) beanFactory);

        this.logger.info("********开始构建alatka-connection配置文件********");
        Instant start = Instant.now();

        for (Resource resource : this.loadResources()) {
            String identity = this.getIdentity(resource);
            RootModel rootModel = this.getRootModel(resource);
            String filename = resource.getFilename();

            this.logger.info("building '{}/{}'...", filename, rootModel.getDesc());
            if (!rootModel.isEnabled()) {
                this.logger.warn("'{}/{}' is disabled, will skip build", filename, rootModel.getDesc());
                continue;
            }

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

        Instant end = Instant.now();
        this.logger.info("********构建alatka-connection配置文件完成，耗时{}ms********", Duration.between(start, end).toMillis());
    }

    /**
     * 加载alatka-connection*.[yml|yaml]
     *
     * @return {@link Resource}集合
     * @throws IllegalArgumentException 加载配置文件失败或者配置文件命名重复
     */
    private List<Resource> loadResources() {
        List<Resource> list = Arrays.stream(FILE_SUFFIX)
                .map(suffix -> ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + this.classpath + FILE_PREFIX + "*" + suffix)
                .map(locationPattern -> {
                    try {
                        return ResourcePatternUtils.getResourcePatternResolver(null)
                                .getResources(locationPattern);
                    } catch (IOException e) {
                        throw new RuntimeException("加载" + FILE_PREFIX + "失败", e);
                    }
                })
                .flatMap(Stream::of)
                .collect(Collectors.toList());

        // 判断配置文件命名是否重复
        Map<String, List<Resource>> map = list.stream()
                .collect(Collectors.groupingBy(resource -> {
                    String filename = resource.getFilename();
                    return filename.substring(0, filename.lastIndexOf("."));
                }));
        map.values().stream()
                .filter(l -> l.size() > 1)
                .findAny()
                .ifPresent(l -> {
                    throw new IllegalArgumentException(FILE_PREFIX + "配置文件命名重复: " + l);
                });

        return Collections.unmodifiableList(list);
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

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.classpath = environment.getProperty(AlatkaConnectionConstant.FILE_CLASSPATH, String.class);
    }
}
