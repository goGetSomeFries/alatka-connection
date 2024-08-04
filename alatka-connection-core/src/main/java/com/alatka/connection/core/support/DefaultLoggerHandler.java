package com.alatka.connection.core.support;

import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;

public class DefaultLoggerHandler extends AbstractReplyProducingMessageHandler {

    private LoggingHandler target;

    public DefaultLoggerHandler(String level) {
        this.target = new LoggingHandler(level);
    }

    @Override
    protected Object handleRequestMessage(Message<?> requestMessage) {
        target.handleMessage(requestMessage);
        return requestMessage;
    }
}
