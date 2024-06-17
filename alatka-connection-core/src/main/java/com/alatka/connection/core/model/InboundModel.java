package com.alatka.connection.core.model;

import com.alatka.connection.core.property.TcpInboundProperty;

public interface InboundModel {

    enum Model {

        tcp(TcpInboundProperty.class),
        http(HttpServerModel.class);

        private Class<? extends InboundModel> type;

        Model(Class<? extends InboundModel> type) {
            this.type = type;
        }
    }
}
