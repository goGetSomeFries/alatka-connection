package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.processors[n].handler.branch
 *
 * @author whocares
 */
public class BranchHandlerProperty extends ChannelAdapterProperty {

    @NotEmpty
    private String identity;

    private String channel;

    @IdentityProperty
    private String taskExecutor;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
