package com.alatka.connection.socket.support;

import org.springframework.integration.mapping.BytesMessageMapper;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Map;

/**
 * 透传
 *
 * @author ybliu
 */
public class PassThroughBytesMessageMapper implements BytesMessageMapper {

    @Override
    public Message<?> toMessage(byte[] bytes, Map<String, Object> headers) {
        return MessageBuilder.withPayload(bytes).copyHeaders(headers).build();
    }

    @Override
    public byte[] fromMessage(Message<?> message) {
        Object payload = message.getPayload();
        if (payload instanceof byte[]) {
            return (byte[]) payload;
        }
        throw new RuntimeException("payload`s type must be byte[]");
    }
}