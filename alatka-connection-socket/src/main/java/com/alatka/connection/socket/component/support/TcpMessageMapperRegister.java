package com.alatka.connection.socket.component.support;

import com.alatka.connection.core.component.support.SupportComponentRegister;
import com.alatka.connection.core.property.socket.TcpMessageMapperProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.connection.TcpMessageMapper;
import org.springframework.integration.mapping.BytesMessageMapper;

/**
 * @author ybliu
 */
public class TcpMessageMapperRegister extends SupportComponentRegister<TcpMessageMapperProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TcpMessageMapperProperty property) {
        Object bytesMessageMapper = ClassUtil.newInstance(property.getClassName());
        if (!(bytesMessageMapper instanceof BytesMessageMapper)) {
            throw new IllegalArgumentException("class must be implements " + BytesMessageMapper.class);
        }

        builder.addPropertyValue("bytesMessageMapper", bytesMessageMapper);
    }

    @Override
    protected Class<TcpMessageMapper> componentClass(TcpMessageMapperProperty property) {
        return TcpMessageMapper.class;
    }

    @Override
    public Class<TcpMessageMapperProperty> mappingKey() {
        return TcpMessageMapperProperty.class;
    }
}
