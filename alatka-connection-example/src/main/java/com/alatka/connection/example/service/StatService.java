package com.alatka.connection.example.service;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class StatService implements CustomMessageHandler<String, String> {

    @Override
    public String doExecute(Message<String> message) {
        return "ok";
    }
}
