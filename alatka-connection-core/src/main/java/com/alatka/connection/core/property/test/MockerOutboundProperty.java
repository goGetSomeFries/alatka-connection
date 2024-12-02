package com.alatka.connection.core.property.test;

import com.alatka.connection.core.property.core.OutboundProperty;

/**
 * alatka.connection.route.outbound.mocker
 *
 * @author ybliu
 */
public class MockerOutboundProperty extends OutboundProperty {

    private String expression;

    private String beanName;

    private String className;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

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
