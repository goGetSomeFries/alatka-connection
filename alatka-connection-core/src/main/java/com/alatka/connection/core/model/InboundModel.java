package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.GatewayInboundProperty;
import com.alatka.connection.core.property.core.InboundProperty;
import com.alatka.connection.core.property.core.RedirectInboundProperty;
import com.alatka.connection.core.property.file.FileV1InboundProperty;
import com.alatka.connection.core.property.file.FileV2InboundProperty;
import com.alatka.connection.core.property.file.FileV3InboundProperty;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import com.alatka.connection.core.property.jdbc.JdbcInboundProperty;
import com.alatka.connection.core.property.socket.TcpDuplexInboundProperty;
import com.alatka.connection.core.property.socket.TcpSimplexInboundProperty;
import com.alatka.connection.core.property.test.MockerInboundProperty;

/**
 * alatka.connection.route.inbound
 *
 * @author ybliu
 */
public enum InboundModel {

    redirect(RedirectInboundProperty.class, false),
    jdbc(JdbcInboundProperty.class, false),
    file_v1(FileV1InboundProperty.class, false),
    file_v2(FileV2InboundProperty.class, false),
    file_v3(FileV3InboundProperty.class, false),
    mocker(MockerInboundProperty.class, false),
    tcp_duplex(TcpDuplexInboundProperty.class, true),
    tcp_simplex(TcpSimplexInboundProperty.class, false),
    gateway(GatewayInboundProperty.class, true),
    http(HttpInboundProperty.class, true);

    /**
     * {@link InboundProperty} {@link Class} 类型
     */
    private final Class<? extends InboundProperty> type;
    /**
     * 双向通信
     */
    private final boolean duplex;

    InboundModel(Class<? extends InboundProperty> type, boolean duplex) {
        this.type = type;
        this.duplex = duplex;
    }

    public Class<? extends InboundProperty> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }
}
