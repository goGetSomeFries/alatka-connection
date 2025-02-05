package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import java.util.Map;

/**
 * TODO
 *
 * @author ybliu
 */
public class SourcePollingInboundProperty extends InboundProperty {

    @IdentityProperty
    private String poller;
    @IdentityProperty
    private String taskScheduler;
    private Map<String, String> headerExpressions;

    public String getPoller() {
        return poller;
    }

    public void setPoller(String poller) {
        this.poller = poller;
    }

    public String getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(String taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public Map<String, String> getHeaderExpressions() {
        return headerExpressions;
    }

    public void setHeaderExpressions(Map<String, String> headerExpressions) {
        this.headerExpressions = headerExpressions;
    }
}
