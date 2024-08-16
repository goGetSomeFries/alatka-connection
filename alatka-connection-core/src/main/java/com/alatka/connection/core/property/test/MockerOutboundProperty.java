package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.core.OutboundProperty;

/**
 * alatka.connection.route.outbound.mocker
 *
 * @author ybliu
 */
public class MockerOutboundProperty extends OutboundProperty {

    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
