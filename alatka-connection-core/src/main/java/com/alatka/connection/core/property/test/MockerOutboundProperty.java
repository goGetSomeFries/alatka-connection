package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.core.OutboundProperty;

/**
 * @author ybliu
 */
public class MockerOutboundProperty extends OutboundProperty {

    private String className;

    private String methodName;

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
