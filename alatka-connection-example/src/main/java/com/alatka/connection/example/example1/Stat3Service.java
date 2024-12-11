package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("example1Stat3Service")
public class Stat3Service implements CustomMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        return new RespMess("00", "ok");
    }
}
