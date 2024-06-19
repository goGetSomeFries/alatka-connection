package com.alatka.connection.core.model;

import com.alatka.connection.core.property.support.SerializerProperty;

import java.util.List;

/**
 * @author ybliu
 */
public class SerializersModel implements DefinitionModel<SerializerProperty> {

    private List<SerializerProperty> serializers;

    public List<SerializerProperty> getSerializers() {
        return serializers;
    }

    public void setSerializers(List<SerializerProperty> serializers) {
        this.serializers = serializers;
    }

    @Override
    public Class<SerializerProperty> propertyClass() {
        return SerializerProperty.class;
    }

    @Override
    public List<SerializerProperty> obtainProperties() {
        return serializers;
    }
}
