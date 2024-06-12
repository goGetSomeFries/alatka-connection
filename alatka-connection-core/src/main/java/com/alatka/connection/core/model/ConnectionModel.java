package com.alatka.connection.core.model;

import java.util.Map;

public class ConnectionModel {

    private RouteModel route;

    private Map<String, Object> definition;

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
    }

    public Map<String, Object> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<String, Object> definition) {
        this.definition = definition;
    }
}
