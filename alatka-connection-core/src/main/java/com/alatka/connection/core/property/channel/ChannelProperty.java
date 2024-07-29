package com.alatka.connection.core.property.channel;

import com.alatka.connection.core.property.Property;

import java.util.Map;

/**
 * @author ybliu
 */
public class ChannelProperty extends Property {

    private Type type;
    private Class<?>[] dataTypes;
    private Map<String, Object> params;

    public enum Type {
        direct(Kind.SUBSCRIBABLE),
        publishSubscribe(Kind.SUBSCRIBABLE),
        null_(Kind.SUBSCRIBABLE),
        queue(Kind.POLLABLE);

        @Deprecated
        private Kind kind;

        @Deprecated
        Type(Kind kind) {
            this.kind = kind;
        }

        @Deprecated
        public Kind getKind() {
            return kind;
        }
    }

    public enum Kind {
        POLLABLE, SUBSCRIBABLE;
    }

    @Override
    public Property defaultProperty() {
        this.type = Type.direct;
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Class<?>[] getDataTypes() {
        return dataTypes;
    }

    public void setDataTypes(Class<?>... dataTypes) {
        this.dataTypes = dataTypes;
    }
}
