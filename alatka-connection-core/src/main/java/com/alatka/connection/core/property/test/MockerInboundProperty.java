package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.core.SourcePollingInboundProperty;

import javax.validation.constraints.NotBlank;

/**
 * alatka.connection.route.inbound.mocker
 *
 * @author ybliu
 */
public class MockerInboundProperty extends SourcePollingInboundProperty {

    @NotBlank
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
