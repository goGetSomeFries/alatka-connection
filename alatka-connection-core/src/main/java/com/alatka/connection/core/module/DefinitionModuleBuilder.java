package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.SupportComponentRegister;
import com.alatka.connection.core.model.DefinitionModel;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public class DefinitionModuleBuilder extends AbstractModuleBuilder<Map<DefinitionModel, Object>, List<? extends Property>> {

    public DefinitionModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends Property> models) {
        Map<Class<Property>, ComponentRegister> mapping =
                SpringFactoriesLoader.loadFactories(SupportComponentRegister.class, null).stream()
                        .collect(Collectors.toMap(SupportComponentRegister::propertyClass, Function.identity()));

        models.forEach(property -> {
            ComponentRegister componentRegister = mapping.get(property.getClass());
            componentRegister.register(property, property.getId(), true);
        });
    }

    @Override
    protected List<? extends Property> convert(Map<DefinitionModel, Object> map) {
        if (map == null) {
            return Collections.emptyList();
        }
        return map.entrySet()
                .stream()
                .flatMap(entry -> entry.getKey().isCollection() ?
                        JsonUtil.convertToList(entry.getValue(), entry.getKey().getType()).stream() :
                        Stream.of(JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType())))
                .map(model -> (Property) model)
                .collect(Collectors.toList());
    }
}
