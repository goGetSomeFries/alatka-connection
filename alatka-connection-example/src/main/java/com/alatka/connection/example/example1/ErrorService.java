package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.example.model.ReqMess;
import com.alatka.connection.example.model.RespMess;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("example1ErrorService")
public class ErrorService implements CustomMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        throw new RuntimeException("执行异常...");
    }
}
