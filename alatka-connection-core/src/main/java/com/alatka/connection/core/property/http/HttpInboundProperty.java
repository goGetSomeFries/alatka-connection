package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.core.InboundProperty;
import com.alatka.connection.core.util.ClassUtil;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * alatka.connection.flow.inbound.http
 *
 * @author ybliu
 */
public class HttpInboundProperty extends InboundProperty {

    @NotEmpty
    private String[] paths;

    private String[] methods;

    private Class<?> requestType;

    private Map<String, String> headerExpressions;

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

    public Map<String, String> getHeaderExpressions() {
        return headerExpressions;
    }

    public void setHeaderExpressions(Map<String, String> headerExpressions) {
        this.headerExpressions = headerExpressions;
    }
}
