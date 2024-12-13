package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.*;

public enum HandlerModel {

    rateLimiter(RateLimiterHandlerProperty.class),
    passthrough(PassthroughHandlerProperty.class),
    logger(LoggerHandlerProperty.class),
    filter(FilterHandlerProperty.class),
    transformer(TransformerHandlerProperty.class),
    null_(NullHandlerProperty.class),
    splitter(SplitterHandlerProperty.class),
    jdbc(JdbcHandlerProperty.class),
    consumer(ConsumerHandlerProperty.class),
    custom(CustomHandlerProperty.class);

    private final Class<? extends ChannelAdapterProperty> type;

    HandlerModel(Class<? extends ChannelAdapterProperty> type) {
        this.type = type;
    }

    public Class<? extends ChannelAdapterProperty> getType() {
        return type;
    }
}
