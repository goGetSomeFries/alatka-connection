package com.alatka.connection.core.property.test;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.property.core.InboundProperty;

/**
 * alatka.connection.route.inbound.mocker
 *
 * @author ybliu
 */
public class MockerInboundProperty extends InboundProperty {

    @IdentityProperty
    private String poller;
    @IdentityProperty
    private String taskScheduler;

    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
