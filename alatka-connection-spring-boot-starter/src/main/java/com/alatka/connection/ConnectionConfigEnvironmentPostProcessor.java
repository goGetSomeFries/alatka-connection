package com.alatka.connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 构造connection默认变量<br>
 * 文件路径：{@link ConnectionConfigEnvironmentPostProcessor#CONNECTION_CONFIG_YML}<br>
 * 参数优先级：application.yml > connection-config.yml
 *
 * @author ybliu
 */
public class ConnectionConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String CONNECTION_CONFIG_YML = "META-INF/connection-config.yml";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource resource = new ClassPathResource(CONNECTION_CONFIG_YML);
        if (resource.exists()) {
            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
            try {
                PropertySource<?> propertySource = loader.load(CONNECTION_CONFIG_YML, resource).get(0);
                environment.getPropertySources().addLast(propertySource);
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
