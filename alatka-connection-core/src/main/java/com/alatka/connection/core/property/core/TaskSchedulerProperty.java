package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * alatka.connection.definition.taskSchedulers
 *
 * @author ybliu
 * @see org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
 */
public class TaskSchedulerProperty extends SupportProperty {

    @NotNull
    private Integer poolSize;
    @NotBlank
    @IdentityProperty
    private String threadNamePrefix;

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

}
