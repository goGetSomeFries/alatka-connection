package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.core.SourcePollingInboundProperty;

/**
 * alatka.connection.flow.inbound.mocker
 *
 * @author ybliu
 */
public class MockerInboundProperty extends SourcePollingInboundProperty {

    private String beanName;

    private String className;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
