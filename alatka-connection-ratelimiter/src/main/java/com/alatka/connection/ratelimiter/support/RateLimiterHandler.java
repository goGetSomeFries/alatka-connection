package com.alatka.connection.ratelimiter.support;

import org.springframework.integration.handler.MessageProcessor;
import org.springframework.messaging.Message;

public class RateLimiterHandler implements MessageProcessor<Object> {

    @Override
    public Object processMessage(Message<?> message) {
        return null;
    }
}
