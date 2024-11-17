package com.alatka.connection.http;

import com.alatka.connection.http.config.HandlerExceptionResolverConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class AutoConfiguration {

    /**
     * 配置{@link HandlerExceptionResolver}，
     * 使{@link HttpRequestHandlingMessagingGateway}支持{@link org.springframework.web.bind.annotation.ExceptionHandler}
     */
    @Bean
    public HandlerExceptionResolverConfigurer handlerExceptionResolverConfigurer(HandlerExceptionResolver handlerExceptionResolver) {
        HandlerExceptionResolverConfigurer resolverConfigurer = new HandlerExceptionResolverConfigurer(handlerExceptionResolver);
        resolverConfigurer.config(HttpRequestHandlingMessagingGateway.class);
        return resolverConfigurer;
    }

}
