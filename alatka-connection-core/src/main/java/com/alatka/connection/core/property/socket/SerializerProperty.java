package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.core.MultiTypeSupportProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.definition.serializers
 *
 * @author ybliu
 */
public class SerializerProperty extends MultiTypeSupportProperty {

    public enum Type implements MultiTypeSupportProperty.Type {

        singleTerminator(SingleTerminator.class);

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

    public static class SingleTerminator extends Params {

        @NotEmpty
        private String delimiter;

        public String getDelimiter() {
            return delimiter;
        }

        public void setDelimiter(String delimiter) {
            this.delimiter = delimiter;
        }
    }
}
