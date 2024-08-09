package com.alatka.connection.socket.component.other;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.socket.TcpConnectionProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.config.TcpConnectionFactoryFactoryBean;
import org.springframework.util.StringUtils;

/**
 * @author ybliu
 */
public class TcpConnectionFactoryFactoryBeanRegister extends AbstractComponentRegister<TcpConnectionProperty, Void> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpConnectionProperty property) {
        builder.addPropertyValue("type", property.isClient() ? "client" : "server")
                .addPropertyValue("port", property.getPort())
                .addPropertyValue("connectTimeout", property.getConnectTimeout() / 1000)
                .addPropertyValue("soTimeout", property.getReadTimeout())
                .addPropertyValue("singleUse", property.isSingleUse())
                .addPropertyValue("usingNio", property.isUsingNio())
                .addPropertyValue("usingDirectBuffers", property.isUsingDirectBuffers())
                .addPropertyValue("soKeepAlive", property.isSoKeepAlive())
                .addPropertyValue("backlog", property.getBacklog())
                .addPropertyValue("soTcpNoDelay", property.isSoTcpNoDelay())
                .addPropertyValue("soLinger", property.getSoLinger());
        if (StringUtils.hasText(property.getHost())) {
            builder.addPropertyValue("host", property.getHost());
        }
        if (property.getSoSendBufferSize() != null) {
            builder.addPropertyValue("soSendBufferSize", property.getSoSendBufferSize());
        }
        if (property.getSoReceiveBufferSize() != null) {
            builder.addPropertyValue("soReceiveBufferSize", property.getSoReceiveBufferSize());
        }
        builder.addPropertyReference("serializer", property.getSerializer());
        builder.addPropertyReference("deserializer", property.getDeserializer());
        builder.addPropertyReference("mapper", property.getTcpMessageMapper());
        if (property.getTaskExecutor() != null) {
            builder.addPropertyReference("taskExecutor", property.getTaskExecutor());
        }
    }

    @Override
    protected Class<TcpConnectionFactoryFactoryBean> componentClass() {
        return TcpConnectionFactoryFactoryBean.class;
    }

    @Override
    public Void mappingKey() {
        return null;
    }
}
