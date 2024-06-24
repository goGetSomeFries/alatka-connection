package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.ChannelAdapterProperty;

/**
 * @author ybliu
 */
public class MockerProperty extends ChannelAdapterProperty {

    private boolean duplex;
    private String poller;

    private String className;
    private String methodName;

    public boolean isDuplex() {
        return duplex;
    }

    public void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }

    public String getPoller() {
        return poller;
    }

    public void setPoller(String poller) {
        this.poller = poller;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
