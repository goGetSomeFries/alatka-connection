package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.support.PollerMetadataProperty;
import com.alatka.connection.core.property.support.SerializerProperty;
import com.alatka.connection.core.property.support.TaskExecutorProperty;
import com.alatka.connection.core.property.support.TaskSchedulerProperty;

/**
 * @author ybliu
 */
public enum DefinitionModel {

    serializers(SerializerProperty.class, true),
    pollerMetadatas(PollerMetadataProperty.class, true),
    taskSchedulers(TaskSchedulerProperty.class, true),
    taskExecutors(TaskExecutorProperty.class, true);

    private Class<? extends Property> type;
    /**
     * 是否是集合类型
     */
    private boolean collection;

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
