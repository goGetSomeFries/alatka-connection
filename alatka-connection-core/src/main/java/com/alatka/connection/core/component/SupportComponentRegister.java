package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;

/**
 * @author ybliu
 */
public abstract class SupportComponentRegister<T extends Property> extends AbstractComponentRegister<T, Class<T>> implements ReferenceProperty<Class<T>> {

}
