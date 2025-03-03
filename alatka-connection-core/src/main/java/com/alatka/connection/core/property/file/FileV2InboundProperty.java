package com.alatka.connection.core.property.file;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.property.core.InboundProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.inbound.file_v2
 *
 * @author ybliu
 */
public class FileV2InboundProperty extends InboundProperty {

    private boolean enableStatusReader = true;

    @NotEmpty
    private String file;

    @IdentityProperty
    private String taskExecutor;

    private Long tailAttemptsDelay;

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

    public boolean isEnableStatusReader() {
        return enableStatusReader;
    }

    public void setEnableStatusReader(boolean enableStatusReader) {
        this.enableStatusReader = enableStatusReader;
    }

    public Long getTailAttemptsDelay() {
        return tailAttemptsDelay;
    }

    public void setTailAttemptsDelay(Long tailAttemptsDelay) {
        this.tailAttemptsDelay = tailAttemptsDelay;
    }
}
