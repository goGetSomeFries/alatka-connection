package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

public interface CustomHandler {

    Message<?> execute(Message<?> message);

}
