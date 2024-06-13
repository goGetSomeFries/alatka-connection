package com.alatka.connection.config.model;

public interface OutboundModel {

    enum Model {

        http(HttpClientModel.class);

        private Class<? extends OutboundModel> type;

        Model(Class<? extends OutboundModel> type) {
            this.type = type;
        }
    }
}
