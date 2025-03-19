package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

public interface ConsumerMessageHandler<T> extends CustomMessageHandler<T, Message<T>> {

    void accept(Message<T> message);

    @Override
    default Message<T> doExecute(Message<T> message) {
        this.accept(message);
        return message;
    }

}
