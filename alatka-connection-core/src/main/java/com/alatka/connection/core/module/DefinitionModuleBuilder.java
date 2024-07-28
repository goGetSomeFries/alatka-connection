package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.SupportComponentRegister;
import com.alatka.connection.core.model.DefinitionModel;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * definition模块构建器<br><br>
 * alatka.connection.definition.taskSchedulers<br>
 * alatka.connection.definition.taskExecutors<br>
 * alatka.connection.definition.pollerMetadatas<br>
 * alatka.connection.definition.......
 *
 * @author ybliu
 */
public class DefinitionModuleBuilder extends AbstractModuleBuilder<Map<DefinitionModel, Object>, List<? extends Property>> {

    public DefinitionModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends Property> models, Map<Object, ? extends ComponentRegister> mapping) {
        models.forEach(property -> {
            ComponentRegister componentRegister = mapping.get(property.getClass());
            componentRegister.register(property, property.getId(), true);
        });
    }

    @Override
    protected List<? extends Property> validAndConvert(Map<DefinitionModel, Object> map) {
        if (map == null) {
            return null;
        }
        return map.entrySet()
                .stream()
                .flatMap(entry -> entry.getKey().isCollection() ?
                        JsonUtil.convertToList(entry.getValue(), entry.getKey().getType()).stream() :
                        Stream.of(JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType())))
                .map(model -> (Property) model)
                .filter(Property::isEnabled)
                .collect(Collectors.toList());
    }

    @Override
    protected Class<SupportComponentRegister> componentRegisterClass() {
        return SupportComponentRegister.class;
    }
}
