package com.alatka.connection.core.property;

import java.util.Map;

public class SerializerProperty extends Property {

    private SerializerName name;

    private Map<String, Object> params;

    public enum SerializerName {
        byteArraySingleTerminatorSerializer
    }

    public SerializerName getName() {
        return name;
    }

    public void setName(SerializerName name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
