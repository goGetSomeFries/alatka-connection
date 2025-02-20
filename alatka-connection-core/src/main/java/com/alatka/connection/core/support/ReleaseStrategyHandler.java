package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

import java.util.Collection;

/**
 * @author whocares
 * @see org.springframework.integration.aggregator.ReleaseStrategy
 * @see com.alatka.connection.core.component.support.ReleaseStrategyRegister
 */
public interface ReleaseStrategyHandler<T> extends CustomMessageHandler<Collection<Message<T>>, Boolean> {

    boolean test(Collection<Message<T>> message);

    @Override
    default Boolean doExecute(Message<Collection<Message<T>>> message) {
        return this.test(message.getPayload());
    }

    @Override
    default boolean returnRawType() {
        return true;
    }
}
