package com.alatka.connection.http.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Optional;

public class HandlerExceptionResolverConfigurer {

    private final Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverConfigurer.class);

    private HandlerExceptionResolver handlerExceptionResolver;

    public HandlerExceptionResolverConfigurer(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    public void config(Class<?>... classes) {
        if (!(this.handlerExceptionResolver instanceof HandlerExceptionResolverComposite)) {
            this.logger.warn("bean 'handlerExceptionResolver' is not type of {}, will skip config...",
                    HandlerExceptionResolverComposite.class.getName());
        } else {
            HandlerExceptionResolverComposite composite = (HandlerExceptionResolverComposite) this.handlerExceptionResolver;
            Optional<ExceptionHandlerExceptionResolver> optional = composite.getExceptionResolvers().stream()
                    .filter(ExceptionHandlerExceptionResolver.class::isInstance)
                    .findFirst()
                    .map(ExceptionHandlerExceptionResolver.class::cast);
            if (!optional.isPresent()) {
                this.logger.warn("not exists instance 'ExceptionHandlerExceptionResolver', will skip config...");
            } else {
                optional.get().setMappedHandlerClasses(classes);
            }
        }
    }
}
