package com.alatka.connection.metric.support;

import com.alatka.connection.core.support.ConsumerMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.history.MessageHistory;
import org.springframework.messaging.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author whocares
 * @see com.alatka.connection.metric.component.handler.TracerHandlerRegister
 */
public class TracerMessageHandler implements ConsumerMessageHandler<Object> {

    private final Logger logger = LoggerFactory.getLogger(TracerMessageHandler.class);

    @Override
    public void accept(Message<Object> message) {
        MessageHistory messageHistory = MessageHistory.read(message);
        if (messageHistory == null) {
            this.logger.warn("'alatka.connection.metric.trace.enabled' must be true");
        } else {
            messageHistory.stream()
                    .map(MessageHistory.Entry.class::cast)
                    .forEach(entry -> {
                        Instant instant = Instant.ofEpochMilli(Long.parseLong(entry.getTimestamp()));
                        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        this.logger.info("{}: {}", entry.getName(), datetime);
                    });
        }
    }
}
