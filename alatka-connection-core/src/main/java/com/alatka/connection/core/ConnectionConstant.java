package com.alatka.connection.core;

import org.springframework.integration.context.IntegrationContextUtils;

/**
 * 常量
 */
public final class ConnectionConstant {

    /**
     * 默认{@link org.springframework.integration.scheduling.PollerMetadata}
     */
    public static final String DEFAULT_POLLER_METADATA = "defaultPollerMetadata";

    /**
     * 默认{@link org.springframework.core.task.TaskExecutor}
     */
    public static final String DEFAULT_TASK_EXECUTOR = "defaultTaskExecutor";

    /**
     * 默认{@link org.springframework.scheduling.TaskScheduler}
     */
    public static final String DEFAULT_TASK_SCHEDULER = "defaultTaskScheduler";

    /**
     * 默认info logger handler
     */
    public static final String DEFAULT_LOGGER_INFO = "defaultLoggerInfo";

    /**
     * 默认info logger channel
     */
    public static final String DEFAULT_LOGGER_INFO_CHANNEL = "defaultLoggerInfoChannel";

    /**
     * 默认error logger handler
     */
    public static final String DEFAULT_LOGGER_ERROR = "defaultLoggerError";

    /**
     * 默认error logger channel
     */
    public static final String DEFAULT_LOGGER_ERROR_CHANNEL = "defaultLoggerErrorChannel";

    /**
     * null channel {@link IntegrationContextUtils#NULL_CHANNEL_BEAN_NAME}
     */
    public static final String DEFAULT_NULL_CHANNEL = IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME;
}
