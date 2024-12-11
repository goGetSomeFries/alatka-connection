package com.alatka.connection.test.support;

import org.springframework.integration.handler.MessageProcessor;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @param <T>
 * @author whocares
 */
public interface InboundMocker<T> extends MessageProcessor<T> {

    T mockInbound();

    @Override
    default T processMessage(Message<?> message) {
        return mockInbound();
    }
}
