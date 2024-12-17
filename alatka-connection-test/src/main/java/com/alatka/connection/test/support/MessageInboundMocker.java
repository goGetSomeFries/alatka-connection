package com.alatka.connection.test.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author whocares
 */
public class MessageInboundMocker implements InboundMocker<String> {

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

}
