package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.MessageGroupStoreProperty;
import org.springframework.integration.store.MessageGroupStore;
import org.springframework.integration.store.SimpleMessageStore;

/**
 * {@link MessageGroupStore}组件注册器
 *
 * @author ybliu
 * @see MessageGroupStore
 */
public class MessageGroupStoreRegister extends MultiTypeSupportComponentRegister<MessageGroupStoreProperty> {

    @Override
    public Class<MessageGroupStoreProperty> mappingKey() {
        return MessageGroupStoreProperty.class;
    }

    @Override
    protected void initialize() {
        super.initialize();

        initComponentClass(MessageGroupStoreProperty.Type.simple, SimpleMessageStore.class);
        initComponentInit(MessageGroupStoreProperty.Type.simple, (builder, params) -> {
        });
    }

}
