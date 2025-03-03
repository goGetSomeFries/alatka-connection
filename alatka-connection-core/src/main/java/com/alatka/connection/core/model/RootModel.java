package com.alatka.connection.core.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Map;

/**
 * alatka.connection
 *
 * @author ybliu
 */
public class RootModel {

    /**
     * alatka.connection.enabled
     */
    private boolean enabled = true;

    /**
     * alatka.connection.desc
     */
    private String desc;

    /**
     * alatka.connection.flow<br>
     * 兼容旧版本alatka.connection.route，之后版本考虑删除
     */
    @JsonAlias({"route"})
    private FlowModel flow;

    /**
     * alatka.connection.definition
     *
     * @see DefinitionModel
     */
    private Map<DefinitionModel, Object> definition;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public FlowModel getFlow() {
        return flow;
    }

    public void setFlow(FlowModel flow) {
        this.flow = flow;
    }

    public Map<DefinitionModel, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<DefinitionModel, Object> definition) {
        this.definition = definition;
    }
}
