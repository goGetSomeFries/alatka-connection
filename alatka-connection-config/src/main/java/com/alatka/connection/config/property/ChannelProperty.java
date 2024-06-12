package com.alatka.connection.config.property;

import java.util.Map;

public class ChannelProperty extends Property {

    private ChannelName name;
    private Class<?>[] dataTypes;
    private Map<String, Object> params;

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

    @Override
    public Property defaultProperty() {
        this.name = ChannelName.direct;
        return this;
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
