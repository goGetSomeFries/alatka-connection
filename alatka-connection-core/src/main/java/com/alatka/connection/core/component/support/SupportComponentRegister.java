package com.alatka.connection.core.component.support;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.ReferencePropertyClass;

/**
 * @author ybliu
 */
public abstract class SupportComponentRegister<T extends Property> extends AbstractComponentRegister<T> implements ReferencePropertyClass<T> {
}
