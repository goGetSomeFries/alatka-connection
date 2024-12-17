package com.alatka.connection.example.example1;

import com.alatka.connection.core.support.CustomMessageHandler;
import com.alatka.connection.example.model.RespMess;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("example1Stat1Service")
public class Stat1Service implements CustomMessageHandler<Map<String, Object>, RespMess> {

    @Override
    public RespMess doExecute(Message<Map<String, Object>> message) {
        Map<String, Object> params = message.getPayload();
        System.out.println(params);
        return new RespMess("00", "ok");
    }
}
