package com.alatka.connection.core.config;

import com.alatka.connection.core.ConnectionConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 默认配置
 *
 * @author ybliu
 */
@Configuration
public class ConnectionFallbackConfig {

    @Bean(ConnectionConstant.FALLBACK_POLLER_METADATA)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_POLLER_METADATA)
    public PollerMetadata pollerMetadata(
            @Value("${alatka.connection.fallback.pollerMetadata.period}") long period,
            @Value("${alatka.connection.fallback.pollerMetadata.maxPerPoll}") long maxPerPoll) {
        PollerMetadata pollerMetadata = new PollerMetadata();
        PeriodicTrigger trigger = new PeriodicTrigger(period);
        trigger.setFixedRate(true);
        pollerMetadata.setTrigger(trigger);
        pollerMetadata.setMaxMessagesPerPoll(maxPerPoll);
        pollerMetadata.setTaskExecutor(taskExecutor(null, null, null, null, null));
        return pollerMetadata;
    }

    @Bean(ConnectionConstant.FALLBACK_TASK_EXECUTOR)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor taskExecutor(
            @Value("${alatka.connection.fallback.taskExecutor.corePoolSize}") Integer corePoolSize,
            @Value("${alatka.connection.fallback.taskExecutor.maxPoolSize}") Integer maxPoolSize,
            @Value("${alatka.connection.fallback.taskExecutor.queueCapacity}") Integer queueCapacity,
            @Value("${alatka.connection.fallback.taskExecutor.keepAliveSeconds}") Integer keepAliveSeconds,
            @Value("${alatka.connection.fallback.taskExecutor.threadNamePrefix}") String threadNamePrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    @Bean(ConnectionConstant.FALLBACK_TASK_SCHEDULER)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_TASK_SCHEDULER)
    public ThreadPoolTaskScheduler taskScheduler(
            @Value("${alatka.connection.fallback.taskScheduler.corePoolSize}") int schedulerCorePoolSize,
            @Value("${alatka.connection.fallback.taskScheduler.threadNamePrefix}") String schedulerThreadNamePrefix) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(schedulerCorePoolSize);
        taskScheduler.setThreadNamePrefix(schedulerThreadNamePrefix);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return taskScheduler;
    }

    @Bean(ConnectionConstant.FALLBACK_LOGGER_INFO_CHANNEL)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_LOGGER_INFO_CHANNEL)
    public MessageChannel infoLoggerChannel() {
        return new DirectChannel();
    }

    @Bean(ConnectionConstant.FALLBACK_LOGGER_INFO)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_LOGGER_INFO)
    @ServiceActivator(inputChannel = ConnectionConstant.FALLBACK_LOGGER_INFO_CHANNEL)
    public LoggingHandler infoLoggingHandler(
            @Value("${alatka.connection.fallback.logger.info}") String expression) {
        LoggingHandler handler = new LoggingHandler(LoggingHandler.Level.INFO);
        handler.setLogExpressionString(expression);
        return handler;
    }

    @Bean(ConnectionConstant.FALLBACK_LOGGER_ERROR_CHANNEL)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_LOGGER_ERROR_CHANNEL)
    public MessageChannel errorLoggerChannel() {
        return new DirectChannel();
    }

    @Bean(ConnectionConstant.FALLBACK_LOGGER_ERROR)
    @ConditionalOnMissingBean(name = ConnectionConstant.FALLBACK_LOGGER_ERROR)
    @ServiceActivator(inputChannel = ConnectionConstant.FALLBACK_LOGGER_ERROR_CHANNEL)
    public LoggingHandler errorLoggingHandler(
            @Value("${alatka.connection.fallback.logger.error}") String expression) {
        LoggingHandler handler = new LoggingHandler(LoggingHandler.Level.ERROR);
        handler.setLogExpressionString(expression);
        return handler;
    }
}
