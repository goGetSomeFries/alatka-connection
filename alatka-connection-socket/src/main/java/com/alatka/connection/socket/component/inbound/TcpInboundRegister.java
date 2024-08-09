package com.alatka.connection.socket.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.property.socket.TcpInboundProperty;
import com.alatka.connection.socket.component.other.TcpConnectionFactoryFactoryBeanRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class TcpInboundRegister<T extends TcpInboundProperty> extends InboundComponentRegister<T> {

    private final TcpConnectionFactoryFactoryBeanRegister register = new TcpConnectionFactoryFactoryBeanRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, T property) {
        String beanName = this.register.register(property.getConnectionFactory());

        builder.addPropertyReference("connectionFactory", beanName);
        builder.addPropertyValue("clientMode", property.isClientMode());
        if (property.getRetryInterval() != null) {
            builder.addPropertyValue("retryInterval", property.getRetryInterval());
        }
    }

}
