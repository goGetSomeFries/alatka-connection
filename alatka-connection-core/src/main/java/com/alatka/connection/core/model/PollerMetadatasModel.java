package com.alatka.connection.core.model;


import com.alatka.connection.core.property.support.PollerMetadataProperty;

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