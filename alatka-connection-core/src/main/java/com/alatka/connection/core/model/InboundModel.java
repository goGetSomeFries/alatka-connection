package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.InboundProperty;
import com.alatka.connection.core.property.core.RedirectInboundProperty;
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

    redirect(RedirectInboundProperty.class, false, false),
    jdbc(JdbcInboundProperty.class, false, false),
    mocker(MockerInboundProperty.class, false, false),
    tcp_duplex(TcpDuplexInboundProperty.class, true, true),
    tcp_simplex(TcpSimplexInboundProperty.class, false, true),
    http(HttpInboundProperty.class, true, true);

    /**
     * {@link InboundProperty} {@link Class} 类型
     */
    private final Class<? extends InboundProperty> type;
    /**
     * 双向通信
     */
    private final boolean duplex;

    private final boolean errorHandle;

    InboundModel(Class<? extends InboundProperty> type, boolean duplex, boolean errorHandle) {
        this.type = type;
        this.duplex = duplex;
        this.errorHandle = errorHandle;
    }

    public Class<? extends InboundProperty> getType() {
        return type;
    }

    public boolean isDuplex() {
        return duplex;
    }

    public boolean isErrorHandle() {
        return errorHandle;
    }
}
