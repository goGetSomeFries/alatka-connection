package com.alatka.connection.core.model;

public interface OutboundModel {

    enum Model {

        http(HttpClientModel.class);

        private Class<? extends OutboundModel> type;

        Model(Class<? extends OutboundModel> type) {
            this.type = type;
        }
    }
}
