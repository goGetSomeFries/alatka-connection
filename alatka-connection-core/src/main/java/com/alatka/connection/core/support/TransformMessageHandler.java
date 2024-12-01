package com.alatka.connection.core.support;

/**
 * @param <T> 方法入参类型
 * @param <S> 方法返回类型
 * @author whocares
 * @see com.alatka.connection.core.component.handler.TransformHandlerRegister
 */
public interface TransformMessageHandler<T, S> extends CustomMessageHandler<T, S> {

    @Override
    default boolean deliveryFailed() {
        return true;
    }
}
