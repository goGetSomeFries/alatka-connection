package com.alatka.connection.test.support;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @param <T>
 * @author whocares
 */
public interface InboundMocker<T> {

    String METHOD_NAME = "mockInbound";

    T doMockInbound();

    default Message<T> mockInbound() {
        T payload = doMockInbound();
        return MessageBuilder.withPayload(payload).build();
    }
}
