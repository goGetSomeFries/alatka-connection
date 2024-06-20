package com.alatka.connection.core.model;

import java.util.Map;

/**
 * @author ybliu
 */
public class RootModel {

    private RouteModel route;
    private Map<DefinitionModel, Object> definition;

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
