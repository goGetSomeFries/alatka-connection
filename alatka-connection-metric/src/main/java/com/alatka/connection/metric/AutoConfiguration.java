package com.alatka.connection.metric;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.history.MessageHistoryConfigurer;

/**
 * @author ybliu
 */
@Configuration
public class AutoConfiguration {

    @Bean(IntegrationContextUtils.INTEGRATION_MESSAGE_HISTORY_CONFIGURER_BEAN_NAME)
    @ConditionalOnProperty(value = "alatka.connection.metric.trace.enabled", havingValue = "true", matchIfMissing = true)
    public MessageHistoryConfigurer messageHistoryConfigurer(
            @Value("${alatka.connection.metric.trace.componentPatterns}") String componentPatterns) {
        MessageHistoryConfigurer configurer = new MessageHistoryConfigurer();
        configurer.setComponentNamePatternsString(componentPatterns);
        return configurer;
    }
}
