package com.alatka.connection.core.support;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public interface CustomHandler<T, S> {

    String METHOD_NAME = "execute";

    S doExecute(Message<T> message);

    default Message<S> execute(Message<T> message) {
        S payload = doExecute(message);
        return MessageBuilder.withPayload(payload).copyHeaders(message.getHeaders()).build();
    }

}
