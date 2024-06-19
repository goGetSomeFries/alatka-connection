package com.alatka.connection.core.model;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.PropertyObtained;
import com.alatka.connection.core.property.ReferencePropertyClass;

/**
 * @author ybliu
 */
public interface DefinitionModel<T extends Property> extends ReferencePropertyClass<T>, PropertyObtained<T> {

    enum Model {

        serializers(SerializersModel.class),
        pollerMetadatas(PollerMetadatasModel.class),
        taskSchedulers(TaskSchedulersModel.class),
        taskExecutors(TaskExecutorsModel.class);

        private Class<? extends DefinitionModel> type;

        Model(Class<? extends DefinitionModel> type) {
            this.type = type;
        }

        public Class<? extends DefinitionModel> type() {
            return type;
        }
    }


}
