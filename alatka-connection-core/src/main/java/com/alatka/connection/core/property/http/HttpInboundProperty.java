package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.core.InboundProperty;
import com.alatka.connection.core.util.ClassUtil;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.route.inbound.http
 *
 * @author ybliu
 */
public class HttpInboundProperty extends InboundProperty {

    @NotEmpty
    private String[] paths;

    private String[] methods;

    private Class<?> requestType;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public String[] getMethods() {
        return methods;
    }

    public void setMethods(String... methods) {
        this.methods = methods;
    }

    public Class<?> getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = ClassUtil.forName(requestType);
    }
}
