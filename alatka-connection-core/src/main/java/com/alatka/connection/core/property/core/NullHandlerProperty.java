package com.alatka.connection.core.property.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * alatka.connection.flow.processors[n].handler.null_
 *
 * @author whocares
 */
public class NullHandlerProperty extends MessageProcessorProperty {

    @JsonIgnore
    @Override
    public String getExpression() {
        return super.getExpression();
    }

    @JsonIgnore
    @Override
    public String getClassName() {
        return super.getClassName();
    }

    @JsonIgnore
    @Override
    public String getBeanName() {
        return super.getBeanName();
    }
}
