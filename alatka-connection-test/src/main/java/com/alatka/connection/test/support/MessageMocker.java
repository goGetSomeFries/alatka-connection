package com.alatka.connection.test.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author whocares
 */
public class MessageMocker implements InboundMocker<String>, OutboundMocker<String, String> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String mockInbound() {
        String payload = "inbound" + counter.incrementAndGet();
        this.logger.info(">>>>>>>>>>>>>>>> inbound >>>>>>>>>>>>>>>>>");
        this.logger.info("inbound send message: {}", payload);
        this.logger.info(">>>>>>>>>>>>>>>> inbound >>>>>>>>>>>>>>>>>");
        return payload;
    }

    @Override
    public String mockOutbound(Message<String> message) {
        this.logger.info(">>>>>>>>>>>>>>>> outbound >>>>>>>>>>>>>>>>>");
        this.logger.info("outbound receive message: {}", message.getPayload());
        this.logger.info(">>>>>>>>>>>>>>>> outbound >>>>>>>>>>>>>>>>>");
        String reply = "outbound" + counter.incrementAndGet();
        this.logger.info("<<<<<<<<<<<<<<<< outbound <<<<<<<<<<<<<<<<<");
        this.logger.info("outbound send message: {}", reply);
        this.logger.info("<<<<<<<<<<<<<<<< outbound <<<<<<<<<<<<<<<<<");
        return reply;
    }
}
