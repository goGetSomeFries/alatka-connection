package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.TransformMessageHandler;
import com.alatka.connection.example.model.ReqMess;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReqMessConvertor implements TransformMessageHandler<byte[], ReqMess> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ReqMess doExecute(Message<byte[]> message) {
        try {
            return objectMapper.readValue(message.getPayload(), ReqMess.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
