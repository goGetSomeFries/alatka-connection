package com.alatka.connection.core.component;

import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.ReferencePropertyClass;

public abstract class InboundComponentRegister<T extends ChannelAdapterProperty> extends AbstractComponentRegister<T> implements ReferencePropertyClass<T> {
}
