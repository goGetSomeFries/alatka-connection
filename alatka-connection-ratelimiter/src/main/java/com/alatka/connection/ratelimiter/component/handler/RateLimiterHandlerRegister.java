package com.alatka.connection.ratelimiter.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.ratelimiter.support.RateLimiterHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

import java.util.Map;

/**
 * TODO
 *
 * @author ybliu
 */
public class RateLimiterHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_PERMITS_PER_SECOND = "permitsPerSecond";
    private static final String KEY_WARMUP_PERIOD = "warmupPeriod";
    private static final String KEY_BLOCKED = "blocked";
    private static final String KEY_TIMEOUT = "timeout";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        Integer permitsPerSecond = getParamsValueOrThrow(params, KEY_PERMITS_PER_SECOND);
        Integer warmupPeriod = getParamsValue(params, KEY_WARMUP_PERIOD);
        boolean blocked = getParamsValue(params, KEY_BLOCKED, false);
        Integer timeout = getParamsValue(params, KEY_TIMEOUT, 0);

        RateLimiterHandler handler = warmupPeriod == null ?
                new RateLimiterHandler(permitsPerSecond) : new RateLimiterHandler(permitsPerSecond, warmupPeriod);
        handler.setBlocked(blocked);
        handler.setTimeout(timeout);
        builder.addConstructorArgValue(handler);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.rateLimiter;
    }
}
