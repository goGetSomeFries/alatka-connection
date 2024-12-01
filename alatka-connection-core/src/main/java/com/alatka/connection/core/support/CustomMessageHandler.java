package com.alatka.connection.core.support;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * @param <T> 方法入参类型
 * @param <S> 方法返回类型
 * @author whocares
 * @see com.alatka.connection.core.component.handler.CustomHandlerRegister
 */
public interface CustomMessageHandler<T, S> {

    String METHOD_NAME = "execute";

    S doExecute(Message<T> message);

    default Map<String, Object> buildHeaders(Message<T> message) {
        return message.getHeaders();
    }

    default boolean deliveryFailed() {
        return false;
    }

    default boolean returnRawType() {
        return false;
    }

    default Object execute(Message<T> message) {
        S payload = doExecute(message);
        if (payload == null) {
            if (this.deliveryFailed()) {
                throw new MessageDeliveryException(message, "payload must not be null");
            }
            return null;
        }
        if (this.returnRawType()) {
            return payload;
        }
        return payload instanceof Message ? (Message<S>) payload :
                MessageBuilder.withPayload(payload).copyHeaders(this.buildHeaders(message)).build();
    }

}
