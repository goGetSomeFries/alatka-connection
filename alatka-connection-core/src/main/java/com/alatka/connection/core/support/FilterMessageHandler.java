package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

/**
 * @param <T> 方法入参类型
 * @author whocares
 * @see com.alatka.connection.core.component.handler.FilterHandlerRegister
 */
public interface FilterMessageHandler<T> extends CustomMessageHandler<T, Boolean> {

    boolean test(Message<T> message);

    @Override
    default Boolean doExecute(Message<T> message) {
        return this.test(message);
    }

    @Override
    default boolean returnRawType() {
        return true;
    }
}
