package com.alatka.connection.core.property.core;

import javax.validation.constraints.NotNull;

/**
 * alatka.connection.definition
 *
 * @author ybliu
 */
public abstract class MultiTypeSupportProperty extends SupportProperty {

    @NotNull
    private String type;

    private Object params;

    public interface Type {

        Class<? extends Params> getClazz();

    }

    public static class Params {
    }

    public abstract Type valueOf(String type);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
