package com.alatka.connection.config.model;

public interface DefinitionModel {

    enum Model {

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
