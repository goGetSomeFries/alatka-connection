package com.alatka.connection.ratelimiter.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.ratelimiter.RateLimiterHandlerProperty;
import com.alatka.connection.ratelimiter.support.RateLimiterHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class RateLimiterHandlerRegister extends HandlerComponentRegister<RateLimiterHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, RateLimiterHandlerProperty property) {
        Integer permitsPerSecond = property.getPermitsPerSecond();
        Integer warmupPeriod = property.getWarmupPeriod();
        boolean blocked = property.getBlocked() != null && property.getBlocked();
        Integer timeout = property.getTimeout() == null ? 0 : property.getTimeout();

        RateLimiterHandler handler = warmupPeriod == null ?
                new RateLimiterHandler(permitsPerSecond) : new RateLimiterHandler(permitsPerSecond, warmupPeriod);
        handler.setBlocked(blocked);
        handler.setTimeout(timeout);
        builder.addConstructorArgValue(handler);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass(RateLimiterHandlerProperty property) {
        return ServiceActivatingHandler.class;
    }

    @Override
    public Class<RateLimiterHandlerProperty> mappingKey() {
        return RateLimiterHandlerProperty.class;
    }
}
