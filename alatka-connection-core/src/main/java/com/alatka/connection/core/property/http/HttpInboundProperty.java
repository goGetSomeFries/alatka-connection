package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.InboundProperty;

/**
 * @author ybliu
 */
public class HttpInboundProperty extends InboundProperty {

    private String[] pathPatterns;

    private String method;

    private Class<?> requestType;

    public String[] getPathPatterns() {
        return pathPatterns;
    }

    public void setPathPatterns(String[] pathPatterns) {
        this.pathPatterns = pathPatterns;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getRequestType() {
        return requestType;
    }

    public void setRequestType(Class<?> requestType) {
        this.requestType = requestType;
    }
}
