package com.alatka.connection.core.model;

public interface InboundModel {

    enum Model {

        http(HttpServerModel.class);

        private Class<? extends InboundModel> type;

        Model(Class<? extends InboundModel> type) {
            this.type = type;
        }
    }
}
