package com.alatka.connection.core.property.core;

import java.util.Map;

/**
 * @author ybliu
 */
public class HandlerProperty extends ChannelAdapterProperty {

    private Type type;
    private Map<String, Object> params;

    public enum Type {
        passthrough, logger, filter, null_, router, splitter, custom;
    }

    @Override
    public HandlerProperty defaultProperty() {
        this.type = Type.passthrough;
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
}
