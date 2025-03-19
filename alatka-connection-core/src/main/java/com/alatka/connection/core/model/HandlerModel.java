package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.property.file.FileSplitterHandlerProperty;
import com.alatka.connection.core.property.file.FileTransformerHandlerProperty;
import com.alatka.connection.core.property.jdbc.JdbcHandlerProperty;
import com.alatka.connection.core.property.metric.TracerHandlerProperty;
import com.alatka.connection.core.property.ratelimiter.RateLimiterHandlerProperty;

/**
 * alatka.connection.flow.processors[n].handler
 *
 * @author ybliu
 */
public enum HandlerModel {

    rateLimiter(RateLimiterHandlerProperty.class),
    passthrough(PassthroughHandlerProperty.class),
    logger(LoggerHandlerProperty.class),
    filter(FilterHandlerProperty.class),
    transformer(TransformerHandlerProperty.class),
    file_transformer(FileTransformerHandlerProperty.class),
    null_(NullHandlerProperty.class),
    splitter(SplitterHandlerProperty.class),
    aggregator(AggregatorHandlerProperty.class),
    file_splitter(FileSplitterHandlerProperty.class),
    jdbc(JdbcHandlerProperty.class),
    subflow(SubflowHandlerProperty.class),
    branch(BranchHandlerProperty.class),
    consumer(ConsumerHandlerProperty.class),
    tracer(TracerHandlerProperty.class),
    custom(CustomHandlerProperty.class);

    private final Class<? extends ChannelAdapterProperty> type;

    HandlerModel(Class<? extends ChannelAdapterProperty> type) {
        this.type = type;
    }

    public Class<? extends ChannelAdapterProperty> getType() {
        return type;
    }
}
