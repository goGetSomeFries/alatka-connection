package com.alatka.connection.core.property;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaskSchedulerProperty extends Property {

    @NotNull
    private Integer poolSize;
    @NotBlank
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
