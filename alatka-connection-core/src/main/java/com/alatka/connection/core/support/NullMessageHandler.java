package com.alatka.connection.core.support;

import org.springframework.integration.handler.MessageProcessor;
import org.springframework.messaging.Message;

/**
 * @author whocares
 * @see com.alatka.connection.core.component.handler.NullHandlerRegister
 */
public class NullMessageHandler implements MessageProcessor<Object> {

    @Override
    public Object processMessage(Message<?> message) {
        return null;
    }
}
