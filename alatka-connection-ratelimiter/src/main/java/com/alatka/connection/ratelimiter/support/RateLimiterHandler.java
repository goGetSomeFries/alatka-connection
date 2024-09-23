package com.alatka.connection.ratelimiter.support;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.handler.MessageProcessor;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeUnit;

public class RateLimiterHandler implements MessageProcessor<Object> {

    private final Logger logger = LoggerFactory.getLogger(RateLimiterHandler.class);

    private RateLimiter rateLimiter;

    private boolean blocked;

    private Integer timeout;

    public RateLimiterHandler(Integer permitsPerSecond) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond.doubleValue());
    }

    public RateLimiterHandler(Integer permitsPerSecond, Integer warmupPeriod) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond, warmupPeriod.longValue(), TimeUnit.SECONDS);
    }

    @Override
    public Object processMessage(Message<?> message) {
        if (blocked) {
            double acquire = this.rateLimiter.acquire();
            this.logger.debug("获取令牌成功，等待时间{}s", acquire);
            return message;
        }
        boolean flag = this.rateLimiter.tryAcquire(this.timeout.longValue(), TimeUnit.SECONDS);
        this.logger.debug("获取令牌{}，等待时间{}s", flag ? "成功" : "失败", this.timeout);
        return flag ? message : null;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
