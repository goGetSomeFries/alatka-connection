package com.alatka.connection.core.property;

import java.util.HashMap;
import java.util.Map;

public class ChannelProperty extends Property {

    private ChannelName name;
    private Class<?>[] dataTypes;
    private Map<String, Object> params = new HashMap<>(0);

    public enum ChannelName {
        direct(ChannelType.SUBSCRIBABLE),
        queue(ChannelType.POLLABLE);

        private ChannelType channelType;

        ChannelName(ChannelType channelType) {
            this.channelType = channelType;
        }

        public ChannelType getChannelType() {
            return channelType;
        }
    }

    public enum ChannelType {
        POLLABLE, SUBSCRIBABLE;
    }

    public ChannelName getName() {
        return name;
    }

    public void setName(ChannelName name) {
        this.name = name;
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
