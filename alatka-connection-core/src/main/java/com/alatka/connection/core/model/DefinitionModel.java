package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.property.socket.SerializerProperty;
import com.alatka.connection.core.property.socket.TcpMessageMapperProperty;

/**
 * alatka.connection.definition
 *
 * @author ybliu
 */
public enum DefinitionModel {

    serializers(SerializerProperty.class, true),
    tcpMessageMappers(TcpMessageMapperProperty.class, true),
    correlationStrategies(CorrelationStrategyProperty.class, true),
    releaseStrategies(ReleaseStrategyProperty.class, true),
    lockRegistries(LockRegistryProperty.class, true),
    messageGroupProcessors(MessageGroupProcessorProperty.class, true),
    messageGroupStores(MessageGroupStoreProperty.class, true),
    pollerMetadata(PollerMetadataProperty.class, true),
    taskSchedulers(TaskSchedulerProperty.class, true),
    taskExecutors(TaskExecutorProperty.class, true);

    /**
     * definition子类型
     */
    private final Class<? extends SupportProperty> type;
    /**
     * 是否是集合类型
     */
    private final boolean collection;

    DefinitionModel(Class<? extends SupportProperty> type, boolean collection) {
        this.type = type;
        this.collection = collection;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isCollection() {
        return collection;
    }

}
