package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.support.SupportComponentRegister;
import com.alatka.connection.core.model.DefinitionModel;
import com.alatka.connection.core.property.core.Property;
import com.alatka.connection.core.property.core.SupportProperty;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * definition模块构建器<br><br>
 * alatka.connection.definition.taskSchedulers<br>
 * alatka.connection.definition.taskExecutors<br>
 * alatka.connection.definition.pollerMetadata<br>
 * alatka.connection.definition.......
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DefinitionModuleBuilder extends AbstractModuleBuilder<Map<DefinitionModel, Object>, List<? extends SupportProperty>> {

    public DefinitionModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends SupportProperty> models, Map<Object, ? extends ComponentRegister> mapping) {
        models.forEach(property -> {
            ComponentRegister componentRegister = super.getComponentRegister(property.getClass(), mapping);
            componentRegister.register(property);
        });
    }

    @Override
    protected List<? extends SupportProperty> validateAndConvert(Map<DefinitionModel, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.entrySet()
                .stream()
                .flatMap(entry -> entry.getKey().isCollection() ?
                        JsonUtil.convertToList(entry.getValue(), entry.getKey().getType()).stream() :
                        Stream.of(JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType())))
                .map(SupportProperty.class::cast)
                .filter(Property::isEnabled)
                .collect(Collectors.toList());
    }

    @Override
    protected Class<SupportComponentRegister> componentRegisterClass() {
        return SupportComponentRegister.class;
    }
}
