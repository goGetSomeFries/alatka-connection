package com.alatka.connection.core.support;

/**
 * @param <T> 方法入参类型
 * @author whocares
 * @see com.alatka.connection.core.component.handler.FilterHandlerRegister
 */
public interface FilterMessageHandler<T> extends CustomMessageHandler<T, Boolean> {

    @Override
    default boolean returnRawType() {
        return true;
    }
}
