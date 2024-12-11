package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("example1Stat2Service")
public class Stat2Service implements CustomMessageHandler<ReqMess, RespMess> {

    @Override
    public RespMess doExecute(Message<ReqMess> message) {
        ReqMess params = message.getPayload();
        System.out.println(params);
        return new RespMess("00", "ok");
    }
}
