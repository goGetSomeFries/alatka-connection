package com.alatka.connection.core.property;

import java.util.Map;

public class HandlerProperty extends Property {

    private HandlerName name;
    private Map<String, Object> params;

    public enum HandlerName {
        passthrough, logger;
    }
}
