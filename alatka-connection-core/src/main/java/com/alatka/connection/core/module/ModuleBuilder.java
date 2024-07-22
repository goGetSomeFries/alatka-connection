package com.alatka.connection.core.module;

import java.util.List;

/**
 * @author ybliu
 */
public interface ModuleBuilder<T> {

    List<String> build(T model);
}
