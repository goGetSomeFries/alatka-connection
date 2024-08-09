package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * @author ybliu
 */
public abstract class Property {

    @IdentityProperty(referenced = false)
    private String id;

    private boolean enabled = true;

    public <T extends Property> T defaultProperty() {
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
