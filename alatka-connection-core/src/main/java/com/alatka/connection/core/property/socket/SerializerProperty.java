package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.core.SupportProperty;

import java.util.Map;

/**
 * alatka.connection.definition.serializers
 *
 * @author ybliu
 */
public class SerializerProperty extends SupportProperty {

    private Type type;

    private Map<String, Object> params;

    public enum Type {
        byteArraySingleTerminatorSerializer,

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
