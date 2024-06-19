package com.alatka.connection.core.property;

import java.util.List;

/**
 * @author ybliu
 */
public interface PropertyObtained<T extends Property> {

    List<T> obtainProperties();
}
