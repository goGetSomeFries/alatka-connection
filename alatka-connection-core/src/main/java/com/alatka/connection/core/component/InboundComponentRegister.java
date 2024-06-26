package com.alatka.connection.core.component;

import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.ReferencePropertyClass;

public abstract class InboundComponentRegister<T extends Property> extends AbstractComponentRegister<T> implements ReferencePropertyClass<T> {
}
