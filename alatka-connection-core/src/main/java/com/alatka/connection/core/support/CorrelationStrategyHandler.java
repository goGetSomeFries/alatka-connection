package com.alatka.connection.core.support;

/**
 * @author whocares
 * @see org.springframework.integration.aggregator.CorrelationStrategy
 * @see com.alatka.connection.core.component.support.CorrelationStrategyRegister
 */
public interface CorrelationStrategyHandler<T, S> extends CustomMessageHandler<T, S> {

    @Override
    default boolean returnRawType() {
        return true;
    }
}
