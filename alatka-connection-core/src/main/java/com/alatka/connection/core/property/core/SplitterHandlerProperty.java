package com.alatka.connection.core.property.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * alatka.connection.flow.processors[n].handler.splitter
 *
 * @author whocares
 */
public class SplitterHandlerProperty extends MessageProcessorProperty {

    @JsonIgnore
    @Override
    public String getExpression() {
        return super.getExpression();
    }

    @JsonIgnore
    @Override
    public String getBeanName() {
        return super.getBeanName();
    }

    @JsonIgnore
    @Override
    public String getClassName() {
        return super.getClassName();
    }
}
