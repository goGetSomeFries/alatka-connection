package com.alatka.connection.core.model;

import com.alatka.connection.core.ConnectionConstant;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefinitionModel {

    private static Map<String, Class<DefinitionModel>> MAP = new HashMap<>();

    static {
        Reflections reflections = new Reflections(ConnectionConstant.PARENT_PACKAGE);
        Set<Class<? extends DefinitionModel>> set = reflections.getSubTypesOf(DefinitionModel.class);
    }

}
