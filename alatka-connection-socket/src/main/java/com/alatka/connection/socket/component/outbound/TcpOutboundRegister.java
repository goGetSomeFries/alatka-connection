package com.alatka.connection.socket.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.socket.TcpConnectionProperty;
import com.alatka.connection.core.property.socket.TcpOutboundProperty;
import com.alatka.connection.socket.component.other.TcpConnectionFactoryFactoryBeanRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public abstract class TcpOutboundRegister<T extends TcpOutboundProperty> extends OutboundComponentRegister<T> {

    private final TcpConnectionFactoryFactoryBeanRegister componentRegister = new TcpConnectionFactoryFactoryBeanRegister();

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
        TcpConnectionProperty connectionFactory = property.getConnectionFactory();
        connectionFactory.setId(property.getId());
        String beanName = this.componentRegister.register(connectionFactory);
        builder.addPropertyReference("connectionFactory", beanName);

        super.postDoRegister(builder, property);
    }
}
