package com.alatka.connection.core.property.core;

import com.alatka.connection.core.model.OutboundModel;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * alatka.connection.flow.outbound.composite
 *
 * @author ybliu
 */
public class CompositeOutboundProperty extends OutboundProperty {

    @NotEmpty
    private List<Map<OutboundModel, Object>> items;

    public List<Map<OutboundModel, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<OutboundModel, Object>> items) {
        this.items = items;
    }

}