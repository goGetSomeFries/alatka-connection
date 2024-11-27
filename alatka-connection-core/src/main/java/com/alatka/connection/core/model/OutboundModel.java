package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.property.http.HttpOutboundProperty;
import com.alatka.connection.core.property.jdbc.JdbcOutboundProperty;
import com.alatka.connection.core.property.socket.TcpDuplexOutboundProperty;
import com.alatka.connection.core.property.socket.TcpSimplexOutboundProperty;
import com.alatka.connection.core.property.test.MockerOutboundProperty;

/**
 * alatka.connection.route.outbound
 *
 * @author ybliu
 */
public enum OutboundModel {

    redirect(RedirectOutboundProperty.class, false),
    router(RouterOutboundProperty.class, false),
    jdbc(JdbcOutboundProperty.class, false),
    custom(CustomOutboundProperty.class, true),
    mocker(MockerOutboundProperty.class, true),
    composite(CompositeOutboundProperty.class, false),
    tcp_simplex(TcpSimplexOutboundProperty.class, false),
    tcp_duplex(TcpDuplexOutboundProperty.class, true),
    http(HttpOutboundProperty.class, true);

    /**
     * {@link OutboundProperty} {@link Class} 类型
     */
    private final Class<? extends OutboundProperty> type;
    /**
     * 双向通信
     */
    private final boolean duplex;

    OutboundModel(Class<? extends OutboundProperty> type, boolean duplex) {
        this.type = type;
        this.duplex = duplex;
    }

    public Class<? extends OutboundProperty> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }
}