package com.alatka.connection.core.property.file;

import com.alatka.connection.core.property.core.SourcePollingInboundProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.inbound.file_v1
 *
 * @author ybliu
 */
public class FileV1InboundProperty extends SourcePollingInboundProperty {

    private boolean scanEachPoll;

    private boolean autoCreateDirectory = true;

    @NotEmpty
    private String directory;

    public boolean isScanEachPoll() {
        return scanEachPoll;
    }

    public void setScanEachPoll(boolean scanEachPoll) {
        this.scanEachPoll = scanEachPoll;
    }

    public boolean isAutoCreateDirectory() {
        return autoCreateDirectory;
    }

    public void setAutoCreateDirectory(boolean autoCreateDirectory) {
        this.autoCreateDirectory = autoCreateDirectory;
    }

    public @NotEmpty String getDirectory() {
        return directory;
    }

    public void setDirectory(@NotEmpty String directory) {
        this.directory = directory;
    }
}
