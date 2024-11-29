package com.alatka.connection.core.support;

public interface FilterMessageHandler<T> extends CustomMessageHandler<T, Boolean> {

    @Override
    default boolean returnRawType() {
        return true;
    }
}
