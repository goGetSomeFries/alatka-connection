package com.alatka.connection.example.example3;

import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.example.model.ReqMess;
import com.alatka.connection.example.model.RespMess;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

public class CustomService implements CustomMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        return new RespMess("00", "ok");
    }
}
