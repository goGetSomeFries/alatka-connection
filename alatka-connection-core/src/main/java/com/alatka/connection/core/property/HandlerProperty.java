package com.alatka.connection.core.property;

import java.util.Map;

/**
 * @author ybliu
 */
public class HandlerProperty extends ChannelAdapterProperty {

    private Type type;
    private Map<String, Object> params;

    public enum Type {
        passthrough, logger;
    }
}
