package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

/**
 * @author whocares
 * @see org.springframework.integration.aggregator.CorrelationStrategy
 * @see com.alatka.connection.core.component.support.CorrelationStrategyRegister
 */
public interface CorrelationStrategyHandler<T, S> extends CustomMessageHandler<T, S> {

    S getCorrelationKey(Message<T> message);

    @Override
    default S doExecute(Message<T> message) {
        return getCorrelationKey(message);
    }

    @Override
    default boolean returnRawType() {
        return true;
    }
}
