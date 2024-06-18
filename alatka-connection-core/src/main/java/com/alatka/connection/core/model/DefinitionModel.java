package com.alatka.connection.core.model;

/**
 * @author ybliu
 */
public interface DefinitionModel {

    enum Model {

        serializers(SerializersModel.class),
        pollerMetadatas(PollerMetadatasModel.class),
        taskSchedulers(TaskSchedulersModel.class),
        taskExecutors(TaskExecutorsModel.class);

        private Class<? extends DefinitionModel> type;

        Model(Class<? extends DefinitionModel> type) {
            this.type = type;
        }

        public Class<? extends DefinitionModel> getType() {
            return type;
        }
    }


}
