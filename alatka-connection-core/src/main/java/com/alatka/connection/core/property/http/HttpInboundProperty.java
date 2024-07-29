package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.InboundProperty;
import com.alatka.connection.core.util.ClassUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ybliu
 */
public class HttpInboundProperty extends InboundProperty {

    @NotNull
    @NotEmpty
    private String[] pathPatterns;

    private String[] methods;

    private Class<?> requestType;

    public String[] getPathPatterns() {
        return pathPatterns;
    }

    public void setPathPatterns(String[] pathPatterns) {
        this.pathPatterns = pathPatterns;
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
