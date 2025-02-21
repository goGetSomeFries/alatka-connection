package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

import java.util.Collection;

/**
 * @author whocares
 * @see org.springframework.integration.aggregator.MessageGroupProcessor
 * @see com.alatka.connection.core.component.support.MessageGroupProcessorRegister
 */
public interface MessageGroupProcessorHandler<T, S> extends CustomMessageHandler<Collection<Message<T>>, S> {

    S processMessageGroup(Collection<Message<T>> messages);

    @Override
    default S doExecute(Message<Collection<Message<T>>> message) {
        return processMessageGroup(message.getPayload());
    }

    @Override
    default boolean returnRawType() {
        return true;
    }
}
