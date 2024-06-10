package com.alatka.connection.core;

import com.alatka.connection.core.component.channel.DirectChannelRegister;
import com.alatka.connection.core.component.channel.QueueChannelRegister;
import com.alatka.connection.core.component.support.PollerMetadataRegister;
import com.alatka.connection.core.component.support.TaskExecutorRegister;
import com.alatka.connection.core.component.support.TaskSchedulerRegister;
import com.alatka.connection.core.config.ConnectionFallbackConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybliu
 */
@Configuration
@ImportAutoConfiguration({ConnectionFallbackConfig.class})
public class AutoConfiguration {

    /* ------------- support ------------- */
    @Bean
    public static TaskExecutorRegister taskExecutorRegister() {
        return new TaskExecutorRegister();
    }

    @Bean
    public static PollerMetadataRegister pollerMetadataRegister() {
        return new PollerMetadataRegister();
    }

    @Bean
    public static TaskSchedulerRegister taskSchedulerRegister() {
        return new TaskSchedulerRegister();
    }

    /* ------------- channel ------------- */
    @Bean
    public static DirectChannelRegister directChannelRegister() {
        return new DirectChannelRegister();
    }

    @Bean
    public static QueueChannelRegister queueChannelRegister() {
        return new QueueChannelRegister();
    }
}
