package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * alatka.connection.definition.taskExecutors
 *
 * @author ybliu
 * @see org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
 */
public class TaskExecutorProperty extends SupportProperty {

    @NotNull
    private Integer corePoolSize;
    @NotNull
    private Integer maxPoolSize;
    @NotNull
    private Integer queueCapacity;
    @NotNull
    private Integer keepAliveSeconds;
    @NotBlank
    @IdentityProperty
    private String threadNamePrefix;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}
