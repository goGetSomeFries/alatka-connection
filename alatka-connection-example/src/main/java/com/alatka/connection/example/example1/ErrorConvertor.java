package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.TransformMessageHandler;
import org.springframework.messaging.Message;

public class ErrorConvertor implements TransformMessageHandler<Throwable, RespMess> {


    @Override
    public RespMess doExecute(Message<Throwable> message) {
        RespMess respMess = new RespMess("error", message.getPayload().getMessage());
        return respMess;
    }
}
