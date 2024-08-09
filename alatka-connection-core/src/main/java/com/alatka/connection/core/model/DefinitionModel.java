package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.PollerMetadataProperty;
import com.alatka.connection.core.property.core.Property;
import com.alatka.connection.core.property.core.TaskExecutorProperty;
import com.alatka.connection.core.property.core.TaskSchedulerProperty;
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
    pollerMetadata(PollerMetadataProperty.class, true),
    taskSchedulers(TaskSchedulerProperty.class, true),
    taskExecutors(TaskExecutorProperty.class, true);

    /**
     * definition子类型
     */
    private final Class<? extends Property> type;
    /**
     * 是否是集合类型
     */
    private final boolean collection;

    DefinitionModel(Class<? extends Property> type, boolean collection) {
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
