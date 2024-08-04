package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

public interface Custom {

    Message<?> execute(Message<?> message);

}
