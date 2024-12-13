package com.alatka.connection.core.support;

import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;

public class LoggerMessageHandler implements ConsumerMessageHandler<Object> {

    private final LoggingHandler target;

    public LoggerMessageHandler() {
        LoggingHandler loggingHandler = new LoggingHandler(LoggingHandler.Level.INFO);
        loggingHandler.setLoggerName(this.getClass().getName());
        this.target = loggingHandler;
    }

    public void setLevel(String level) {
        this.target.setLevel(LoggingHandler.Level.valueOf(level));
    }

    public void setLoggerName(String loggerName) {
        this.target.setLoggerName(loggerName);
    }

    public void setExpression(String expression) {
        this.target.setLogExpressionString(expression);
    }

    @Override
    public void accept(Message<Object> message) {
        this.target.handleMessage(message);
    }
}
