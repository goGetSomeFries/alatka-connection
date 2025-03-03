package com.alatka.connection.core.support;

import org.springframework.integration.handler.MessageProcessor;
import org.springframework.messaging.Message;

/**
 * @author whocares
 * @see com.alatka.connection.core.component.handler.NullHandlerRegister
 */
public class NullMessageHandler implements CustomMessageHandler<Object, Object> {

    @Override
    public Object doExecute(Message<Object> message) {
        return null;
    }
}
