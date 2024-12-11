package com.alatka.connection.test.support;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.messaging.Message;

/**
 * TODO
 *
 * @param <T>
 * @param <S>
 * @author ybliu
 */
public interface OutboundMocker<T, S> extends CustomMessageHandler<T, S> {

    S mockOutbound(Message<T> message);

    @Override
    default S doExecute(Message<T> message) {
        return mockOutbound(message);
    }
}
