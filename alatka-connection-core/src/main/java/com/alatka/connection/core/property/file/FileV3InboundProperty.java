package com.alatka.connection.core.property.file;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.property.core.InboundProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.inbound.file_v3
 *
 * @author ybliu
 */
public class FileV3InboundProperty extends InboundProperty {

    private Long delay;

    private Boolean end;

    private Boolean reopen;

    @NotEmpty
    private String file;

    @IdentityProperty
    private String taskExecutor;

    private Long tailAttemptsDelay;

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Boolean getReopen() {
        return reopen;
    }

    public void setReopen(Boolean reopen) {
        this.reopen = reopen;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Long getTailAttemptsDelay() {
        return tailAttemptsDelay;
    }

    public void setTailAttemptsDelay(Long tailAttemptsDelay) {
        this.tailAttemptsDelay = tailAttemptsDelay;
    }
}
