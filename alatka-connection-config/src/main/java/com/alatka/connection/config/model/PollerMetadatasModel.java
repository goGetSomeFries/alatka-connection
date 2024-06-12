package com.alatka.connection.config.model;


import com.alatka.connection.config.property.PollerMetadataProperty;

import java.util.List;

/**
 * @author ybliu
 */
public class PollerMetadatasModel implements DefinitionModel {

    private List<PollerMetadataProperty> pollerMetadatas;

    public List<PollerMetadataProperty> getPollerMetadatas() {
        return pollerMetadatas;
    }

    public void setPollerMetadatas(List<PollerMetadataProperty> pollerMetadatas) {
        this.pollerMetadatas = pollerMetadatas;
    }
}