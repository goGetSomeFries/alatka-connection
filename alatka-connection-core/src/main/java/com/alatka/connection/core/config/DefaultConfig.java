package com.alatka.connection.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.NullChannel;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.ThreadPoolExecutor;

import static com.alatka.connection.core.AlatkaConnectionConstant.FALLBACK_PREFIX;

/**
 * 默认配置
 *
 * @author ybliu
 */
@Configuration
public class DefaultConfig {

    /**
     * 默认{@link org.springframework.integration.scheduling.PollerMetadata}
     */
    public static final String FALLBACK_POLLER_METADATA = FALLBACK_PREFIX + "pollerMetadata";
    /**
     * 默认{@link org.springframework.core.task.TaskExecutor}
     */
    public static final String FALLBACK_TASK_EXECUTOR = FALLBACK_PREFIX + "taskExecutor";
    /**
     * 默认{@link org.springframework.scheduling.TaskScheduler}
     */
    public static final String FALLBACK_TASK_SCHEDULER = FALLBACK_PREFIX + "taskScheduler";
    /**
     * 默认info logger handler
     */
    public static final String FALLBACK_LOGGER_INFO = FALLBACK_PREFIX + "loggerInfo";
    /**
     * 默认info logger channel
     */
    public static final String FALLBACK_LOGGER_INFO_CHANNEL = FALLBACK_PREFIX + "loggerInfoChannel";
    /**
     * 默认error logger handler
     */
    public static final String FALLBACK_LOGGER_ERROR = FALLBACK_PREFIX + "loggerError";
    /**
     * 默认error logger channel
     */
    public static final String FALLBACK_LOGGER_ERROR_CHANNEL = FALLBACK_PREFIX + "loggerErrorChannel";
    /**
     * null channel {@link IntegrationContextUtils#NULL_CHANNEL_BEAN_NAME}
     */
    public static final String FALLBACK_NULL_CHANNEL = FALLBACK_PREFIX + "nullChannel";

    @Bean(FALLBACK_POLLER_METADATA)
    @ConditionalOnMissingBean(name = FALLBACK_POLLER_METADATA)
    public PollerMetadata pollerMetadata(
            @Value("${alatka.connection.default.pollerMetadata.period}") long period,
            @Value("${alatka.connection.default.pollerMetadata.maxPerPoll}") long maxPerPoll) {
        PollerMetadata pollerMetadata = new PollerMetadata();
        PeriodicTrigger trigger = new PeriodicTrigger(period);
        trigger.setFixedRate(true);
        pollerMetadata.setTrigger(trigger);
        pollerMetadata.setMaxMessagesPerPoll(maxPerPoll);
        pollerMetadata.setTaskExecutor(taskExecutor(null, null, null, null, null));
        return pollerMetadata;
    }

    @Bean(FALLBACK_TASK_EXECUTOR)
    @ConditionalOnMissingBean(name = FALLBACK_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor taskExecutor(
            @Value("${alatka.connection.default.taskExecutor.corePoolSize}") Integer corePoolSize,
            @Value("${alatka.connection.default.taskExecutor.maxPoolSize}") Integer maxPoolSize,
            @Value("${alatka.connection.default.taskExecutor.queueCapacity}") Integer queueCapacity,
            @Value("${alatka.connection.default.taskExecutor.keepAliveSeconds}") Integer keepAliveSeconds,
            @Value("${alatka.connection.default.taskExecutor.threadNamePrefix}") String threadNamePrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    @Bean(FALLBACK_TASK_SCHEDULER)
    @ConditionalOnMissingBean(name = FALLBACK_TASK_SCHEDULER)
    public ThreadPoolTaskScheduler taskScheduler(
            @Value("${alatka.connection.default.taskScheduler.corePoolSize}") int schedulerCorePoolSize,
            @Value("${alatka.connection.default.taskScheduler.threadNamePrefix}") String schedulerThreadNamePrefix) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(schedulerCorePoolSize);
        taskScheduler.setThreadNamePrefix(schedulerThreadNamePrefix);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return taskScheduler;
    }

    @Bean(FALLBACK_LOGGER_INFO_CHANNEL)
    @ConditionalOnMissingBean(name = FALLBACK_LOGGER_INFO_CHANNEL)
    public MessageChannel infoLoggerChannel() {
        return new DirectChannel();
    }

    @Bean(FALLBACK_LOGGER_INFO)
    @ConditionalOnMissingBean(name = FALLBACK_LOGGER_INFO)
    @ServiceActivator(inputChannel = FALLBACK_LOGGER_INFO_CHANNEL)
    public LoggingHandler infoLoggingHandler(
            @Value("${alatka.connection.default.logger.info}") String expression) {
        LoggingHandler handler = new LoggingHandler(LoggingHandler.Level.INFO);
        handler.setLogExpressionString(expression);
        return handler;
    }

    @Bean(FALLBACK_LOGGER_ERROR_CHANNEL)
    @ConditionalOnMissingBean(name = FALLBACK_LOGGER_ERROR_CHANNEL)
    public MessageChannel errorLoggerChannel() {
        return new DirectChannel();
    }

    @Bean(FALLBACK_LOGGER_ERROR)
    @ConditionalOnMissingBean(name = FALLBACK_LOGGER_ERROR)
    @ServiceActivator(inputChannel = FALLBACK_LOGGER_ERROR_CHANNEL)
    public LoggingHandler errorLoggingHandler(
            @Value("${alatka.connection.default.logger.error}") String expression) {
        LoggingHandler handler = new LoggingHandler(LoggingHandler.Level.ERROR);
        handler.setLogExpressionString(expression);
        return handler;
    }

    @Bean(FALLBACK_NULL_CHANNEL)
    @ConditionalOnMissingBean(name = FALLBACK_NULL_CHANNEL)
    public MessageChannel nullChannel() {
        return new NullChannel();
    }
}
