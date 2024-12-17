package com.alatka.connection.example.example3;

import com.alatka.connection.core.support.FilterMessageHandler;
import com.alatka.connection.example.model.ReqMess;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ExampleFilter implements FilterMessageHandler<ReqMess> {

    @Override
    public boolean test(Message<ReqMess> message) {
        ReqMess payload = message.getPayload();
        return payload.getTopic() != null;
    }
}
