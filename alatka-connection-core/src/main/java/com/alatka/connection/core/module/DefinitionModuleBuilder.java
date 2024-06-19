package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.support.SupportComponentRegister;
import com.alatka.connection.core.model.DefinitionModel;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class DefinitionModuleBuilder extends AbstractModuleBuilder<Map<DefinitionModel.Model, Map<String, Object>>, List<? extends DefinitionModel>> {

    @Override
    public void doBuild(List<? extends DefinitionModel> models) {
        Map<Class<Property>, ComponentRegister> mapping =
                getApplicationContext().getBeansOfType(SupportComponentRegister.class).values()
                        .stream()
                        .collect(Collectors.toMap(SupportComponentRegister::propertyClass, Function.identity()));

        models.forEach(model -> {
            ComponentRegister componentRegister = mapping.get(model.propertyClass());
            List<Property> list = model.obtainProperties();
            list.forEach(property -> componentRegister.register(property, property.getId(), true));
        });
    }

    @Override
    protected List<? extends DefinitionModel> convert(Map<DefinitionModel.Model, Map<String, Object>> model) {
        return model.entrySet()
                .stream()
                .map(entry -> JsonUtil.mapToObject(entry.getValue(), entry.getKey().type()))
                .collect(Collectors.toList());
    }
}
