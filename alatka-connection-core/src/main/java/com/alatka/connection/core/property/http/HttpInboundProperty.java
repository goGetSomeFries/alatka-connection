package com.alatka.connection.core.property.http;

import com.alatka.connection.core.property.InboundProperty;

/**
 * @author ybliu
 */
public class HttpInboundProperty extends InboundProperty {

    private String[] pathPatterns;

    private String method;

    private Class<?> requestType;
}
