package com.alatka.connection.example.service;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class LoggerService implements CustomMessageHandler<Object, Object> {

    private final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    @Override
    public Object doExecute(Message<Object> message) {
        Object payload = message.getPayload();
        logger.info("Received payload: {}", payload);
        return payload;
    }
}
