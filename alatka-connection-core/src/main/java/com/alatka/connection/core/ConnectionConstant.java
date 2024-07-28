package com.alatka.connection.core;

import org.springframework.integration.context.IntegrationContextUtils;

/**
 * 常量
 *
 * @author ybliu
 */
public final class ConnectionConstant {

    /**
     * 默认{@link org.springframework.integration.scheduling.PollerMetadata}
     */
    public static final String FALLBACK_POLLER_METADATA = "fallback@pollerMetadata";
    /**
     * 默认{@link org.springframework.core.task.TaskExecutor}
     */
    public static final String FALLBACK_TASK_EXECUTOR = "fallback@taskExecutor";
    /**
     * 默认{@link org.springframework.scheduling.TaskScheduler}
     */
    public static final String FALLBACK_TASK_SCHEDULER = "fallback@taskScheduler";
    /**
     * 默认info logger handler
     */
    public static final String FALLBACK_LOGGER_INFO = "fallback@loggerInfo";
    /**
     * 默认info logger channel
     */
    public static final String FALLBACK_LOGGER_INFO_CHANNEL = "fallback@loggerInfoChannel";
    /**
     * 默认error logger handler
     */
    public static final String FALLBACK_LOGGER_ERROR = "fallback@loggerError";
    /**
     * 默认error logger channel
     */
    public static final String FALLBACK_LOGGER_ERROR_CHANNEL = "fallback@loggerErrorChannel";
    /**
     * null channel {@link IntegrationContextUtils#NULL_CHANNEL_BEAN_NAME}
     */
    public static final String FALLBACK_NULL_CHANNEL = "fallback@nullChannel";

    // ==========================================================================================

    public static final String INBOUND_INPUT_CHANNEL = "channel.inbound.input";
    public static final String INBOUND_OUTPUT_CHANNEL = "channel.inbound.output";
    public static final String OUTBOUND_INPUT_CHANNEL = "channel.outbound.input";
    public static final String OUTBOUND_OUTPUT_CHANNEL = "channel.outbound.output";
    public static final String BYPASS_INPUT_CHANNEL = OUTBOUND_INPUT_CHANNEL;
    public static final String BYPASS_OUTPUT_CHANNEL = FALLBACK_NULL_CHANNEL;
}
