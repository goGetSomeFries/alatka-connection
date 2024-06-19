package com.alatka.connection.core.model;

import java.util.Map;

/**
 * @author ybliu
 */
public class RootModel {

    private RouteModel route;

    /**
     * {@link DefinitionModel}
     */
    private Map<DefinitionModel.Model, Map<String, Object>> definition;

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
    }

    public Map<DefinitionModel.Model, Map<String, Object>> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<DefinitionModel.Model, Map<String, Object>> definition) {
        this.definition = definition;
    }
}
