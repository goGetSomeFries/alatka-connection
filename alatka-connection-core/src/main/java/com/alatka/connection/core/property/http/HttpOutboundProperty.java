package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.core.OutboundProperty;
import com.alatka.connection.core.util.ClassUtil;

/**
 * alatka.connection.flow.outbound.http
 *
 * @author ybliu
 */
public class HttpOutboundProperty extends OutboundProperty {

    private String url;

    private String method;

    private Class<?> responseType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?> getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = ClassUtil.forName(responseType);
    }
}
