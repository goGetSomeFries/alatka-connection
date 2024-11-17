package com.alatka.connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 构造alatka-connection默认变量<br>
 * alatka-connection-core alatka-connection-http alatka-connection-socket等模块中如使用变量可在该文件中进行配置<br>
 * 文件路径：{@link AlatkaConnectionConfigEnvironmentPostProcessor#CONNECTION_CONFIG_YML}<br>
 * 参数优先级：application.yml > alatka.connection.config.yaml，可通过配置application.yml中进行覆盖<br>
 *
 * @author ybliu
 */
public class AlatkaConnectionConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String CONNECTION_CONFIG_YML = "META-INF/alatka.connection.config.yaml";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource resource = new ClassPathResource(CONNECTION_CONFIG_YML);
        if (resource.exists()) {
            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
            try {
                loader.load(CONNECTION_CONFIG_YML, resource)
                        .forEach(propertySource -> environment.getPropertySources().addLast(propertySource));
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load gateway config properties from " + resource, e);
            }
        }

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
