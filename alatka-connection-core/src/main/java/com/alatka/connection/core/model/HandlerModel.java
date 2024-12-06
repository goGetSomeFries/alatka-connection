package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.*;

public enum HandlerModel {

    rateLimiter(RateLimiterHandlerProperty.class),
    passthrough(PassthroughHandlerProperty.class),
    logger(null),
    filter(FilterHandlerProperty.class),
    transformer(TransformerHandlerProperty.class),
    null_(null),
    splitter(null),
    jdbc(null),
    custom(CustomHandlerProperty.class);

    private final Class<? extends ChannelAdapterProperty> type;

    HandlerModel(Class<? extends ChannelAdapterProperty> type) {
        this.type = type;
    }

    public Class<? extends ChannelAdapterProperty> getType() {
        return type;
    }
}
