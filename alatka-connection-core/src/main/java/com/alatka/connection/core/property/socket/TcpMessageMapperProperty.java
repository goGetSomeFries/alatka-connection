package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.core.SupportProperty;

import javax.validation.constraints.NotBlank;

/**
 * alatka.connection.definition.tcpMessageMappers
 *
 * @author ybliu
 */
public class TcpMessageMapperProperty extends SupportProperty {

    /**
     * {@link org.springframework.integration.mapping.BytesMessageMapper} className
     */
    @NotBlank
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
