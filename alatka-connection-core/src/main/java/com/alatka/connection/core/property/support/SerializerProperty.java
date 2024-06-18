package com.alatka.connection.core.property.support;

import com.alatka.connection.core.property.Property;

import java.util.Map;

/**
 * @author ybliu
 */
public class SerializerProperty extends Property {

    private Type type;

    private Map<String, Object> params;

    public enum Type {
        byteArraySingleTerminatorSerializer(),

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
}
