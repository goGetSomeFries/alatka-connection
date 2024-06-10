package com.alatka.connection.core.property;

public class Property {

    private String id;

    public Property defaultProperty() {
        throw new UnsupportedOperationException("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
