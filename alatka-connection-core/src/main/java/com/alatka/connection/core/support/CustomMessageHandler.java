package com.alatka.connection.core.support;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

public interface CustomMessageHandler<T, S> {

    String METHOD_NAME = "execute";

    S doExecute(Message<T> message);

    default Map<String, Object> buildHeaders(Message<T> message) {
        return message.getHeaders();
    }

    default boolean deliveryFailed() {
        return false;
    }

    default Message<S> execute(Message<T> message) {
        S payload = doExecute(message);
        if (payload == null) {
            if (this.deliveryFailed()) {
                throw new MessageDeliveryException(message, "payload must not be null");
            }
            return null;
        }
        return payload instanceof Message ? (Message<S>) payload :
                MessageBuilder.withPayload(payload).copyHeaders(this.buildHeaders(message)).build();
    }

}
