package com.alatka.connection.core.model;

import java.util.Map;

public class ConnectionModel {

    private RouteModel route;

    /**
     * {@link DefinitionModel}
     */
    private Map<DefinitionModel.Model, Object> definition;

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
    }

    public Map<DefinitionModel.Model, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<DefinitionModel.Model, Object> definition) {
        this.definition = definition;
    }
}
