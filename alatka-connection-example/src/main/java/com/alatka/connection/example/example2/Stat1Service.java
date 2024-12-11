package com.alatka.connection.example.example2;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("example2Stat1Service")
public class Stat1Service implements CustomMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        throw new RuntimeException("执行异常...");
    }
}
