package com.alatka.connection.core.property.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoggerHandlerProperty extends MessageProcessorProperty {

    private String level;

    private String loggerName;

    private String logExpression;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLogExpression() {
        return logExpression;
    }

    public void setLogExpression(String logExpression) {
        this.logExpression = logExpression;
    }

    @JsonIgnore
    @Override
    public String getExpression() {
        return super.getExpression();
    }

    @JsonIgnore
    @Override
    public String getClassName() {
        return super.getClassName();
    }

    @JsonIgnore
    @Override
    public String getBeanName() {
        return super.getBeanName();
    }
}
