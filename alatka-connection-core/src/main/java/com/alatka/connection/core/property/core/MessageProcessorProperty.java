package com.alatka.connection.core.property.core;

public abstract class MessageProcessorProperty extends ChannelAdapterProperty {

    private String expression;

    private String className;

    private String beanName;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
