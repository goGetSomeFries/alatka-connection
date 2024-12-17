package com.alatka.connection.example.example3;

import com.alatka.connection.core.support.TransformMessageHandler;
import com.alatka.connection.example.model.ReqMess;
import com.alatka.connection.example.model.RespMess;
import org.springframework.messaging.Message;

public class TransformerService implements TransformMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        return new RespMess("00", "ok");
    }
}
