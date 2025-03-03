package com.alatka.connection.core.property.core;

/**
 * alatka.connection.definition.messageGroupStores
 *
 * @author ybliu
 * @see org.springframework.integration.store.MessageGroupStore
 */
public class MessageGroupStoreProperty extends MultiTypeSupportProperty {

    public enum Type implements MultiTypeSupportProperty.Type {

        simple(Simple.class);

        private final Class<? extends Params> clazz;

        Type(Class<? extends Params> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class<? extends Params> getClazz() {
            return clazz;
        }

    }

    @Override
    public Type valueOf(String type) {
        return Type.valueOf(type);
    }

    public static class Simple extends Params {

    }
}
