package com.alatka.connection.socket.component.support;

import com.alatka.connection.core.component.support.MultiTypeSupportComponentRegister;
import com.alatka.connection.core.property.socket.SerializerProperty;
import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;

/**
 * {@link AbstractByteArraySerializer}组件注册器
 *
 * @author ybliu
 * @see AbstractByteArraySerializer
 */
public class ByteArraySingleTerminatorSerializerRegister extends MultiTypeSupportComponentRegister<SerializerProperty> {

    @Override
    protected void initialize() {
        super.initialize();

        initComponentClass(SerializerProperty.Type.singleTerminator, ByteArraySingleTerminatorSerializer.class);
        initComponentInit(SerializerProperty.Type.singleTerminator, (builder, params) -> {
            SerializerProperty.SingleTerminator singleTerminator = (SerializerProperty.SingleTerminator) params;
            builder.addConstructorArgValue(singleTerminator.getDelimiter());
        });
    }

    @Override
    public Class<SerializerProperty> mappingKey() {
        return SerializerProperty.class;
    }
}
