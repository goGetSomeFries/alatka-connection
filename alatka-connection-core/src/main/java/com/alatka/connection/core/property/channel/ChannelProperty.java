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
        direct,
        publishSubscribe,
        null_,
        queue;
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
