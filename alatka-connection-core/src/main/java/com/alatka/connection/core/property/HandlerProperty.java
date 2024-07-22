package com.alatka.connection.core.property;

import java.util.Map;

public class HandlerProperty extends Property {

    private Type type;
    private Map<String, Object> params;

    public enum Type {
        passthrough, logger;
    }
}