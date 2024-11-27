package com.alatka.connection.core.support;

public interface TransformMessageHandler<T, S> extends CustomMessageHandler<T, S> {

    @Override
    default boolean deliveryFailed() {
        return true;
    }
}
