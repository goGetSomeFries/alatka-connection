package com.alatka.connection.core.model;

import com.alatka.connection.core.property.PollerMetadataProperty;

import java.util.List;

/**
 * @author ybliu
 */
public class PollerMetadatasModel extends DefinitionModel {

    private List<PollerMetadataProperty> pollerMetadatas;

    public List<PollerMetadataProperty> getPollerMetadatas() {
        return pollerMetadatas;
    }

    public void setPollerMetadatas(List<PollerMetadataProperty> pollerMetadatas) {
        this.pollerMetadatas = pollerMetadatas;
    }
}