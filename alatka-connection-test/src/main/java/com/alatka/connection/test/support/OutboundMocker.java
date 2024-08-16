package com.alatka.connection.test.support;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @param <T>
 * @param <S>
 * @author ybliu
 */
public interface OutboundMocker<T, S> {

    String METHOD_NAME = "mockOutbound";

    S doMockOutbound(Message<T> message);

    default Message<S> mockOutbound(Message<T> message) {
        S payload = doMockOutbound(message);
        return MessageBuilder.withPayload(payload).copyHeaders(message.getHeaders()).build();
    }
}
