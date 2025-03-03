package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.property.socket.TcpConnectionProperty;
import com.alatka.connection.core.property.socket.TcpInboundProperty;
import com.alatka.connection.socket.component.other.TcpConnectionFactoryRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * tcp组件注册器
 *
 * @author ybliu
 */
public abstract class TcpInboundRegister<T extends TcpInboundProperty> extends InboundComponentRegister<T> {

    private final TcpConnectionFactoryRegister componentRegister = new TcpConnectionFactoryRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, T property) {
        TcpConnectionProperty connectionFactory = property.getConnectionFactory();
        connectionFactory.setId(property.getId());
        if (connectionFactory.getClient() == null) {
            connectionFactory.setClient(false);
        }
        String beanName = this.componentRegister.register(connectionFactory);

        builder.addPropertyReference("connectionFactory", beanName);
        builder.addPropertyValue("clientMode", property.isClientMode());
        if (property.getRetryInterval() != null) {
            builder.addPropertyValue("retryInterval", property.getRetryInterval());
        }
    }

}
