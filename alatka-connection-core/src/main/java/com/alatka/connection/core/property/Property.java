package com.alatka.connection.core.property;

/**
 * @author ybliu
 */
public class Property {

    private String id;

    private boolean enabled = true;

    public Property defaultProperty() {
        throw new UnsupportedOperationException("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
