package com.alatka.connection.core.model;

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
     * alatka.connection.route
     */
    private RouteModel route;

    /**
     * alatka.connection.definition
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

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
    }

    public Map<DefinitionModel, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<DefinitionModel, Object> definition) {
        this.definition = definition;
    }
}
