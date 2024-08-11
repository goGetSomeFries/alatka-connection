package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.core.Property;

import javax.validation.constraints.NotBlank;

/**
 * alatka.connection.definition.serializers
 *
 * @author ybliu
 */
public class TcpMessageMapperProperty extends Property {

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
