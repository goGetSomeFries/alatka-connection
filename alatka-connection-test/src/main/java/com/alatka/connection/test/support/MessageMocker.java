package com.alatka.connection.test.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageMocker {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicInteger counter = new AtomicInteger(0);

    public String inbound() {
        String payload = "inbound" + counter.incrementAndGet();
        this.logger.info(">>>>>>>>>>>>>>>> inbound >>>>>>>>>>>>>>>>>");
        this.logger.info("inbound send message: " + payload);
        this.logger.info(">>>>>>>>>>>>>>>> inbound >>>>>>>>>>>>>>>>>");
        return payload;
    }

    public String outbound(String payload) {
        this.logger.info(">>>>>>>>>>>>>>>> outbound >>>>>>>>>>>>>>>>>");
        this.logger.info("outbound receive message: " + payload);
        this.logger.info(">>>>>>>>>>>>>>>> outbound >>>>>>>>>>>>>>>>>");
        String reply = "outbound" + counter.incrementAndGet();
        this.logger.info("<<<<<<<<<<<<<<<< outbound <<<<<<<<<<<<<<<<<");
        this.logger.info("outbound send message: " + reply);
        this.logger.info("<<<<<<<<<<<<<<<< outbound <<<<<<<<<<<<<<<<<");
        return reply;
    }
}
