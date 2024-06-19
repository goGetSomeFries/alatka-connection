package com.alatka.connection.core.property;

import com.alatka.connection.core.property.Property;

/**
 * @author ybliu
 */
public interface ReferencePropertyClass<T extends Property> {

    Class<T> propertyClass();
}
