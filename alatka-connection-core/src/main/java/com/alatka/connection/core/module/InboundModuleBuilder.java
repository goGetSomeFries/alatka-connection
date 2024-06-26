package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class InboundModuleBuilder extends AbstractModuleBuilder<Map<InboundModel, Object>, List<? extends Property>> {

    @Override
    protected void doBuild(List<? extends Property> models) {
        Map<Class<Property>, ComponentRegister> mapping =
                SpringFactoriesLoader.loadFactories(InboundComponentRegister.class, null)
                        .stream()
                        .collect(Collectors.toMap(InboundComponentRegister::propertyClass, Function.identity()));

        models.forEach(property -> {
            ComponentRegister componentRegister = mapping.get(property.getClass());
            componentRegister.register(property, property.getId(), true);
        });
    }

    @Override
    protected List<? extends Property> convert(Map<InboundModel, Object> map) {
        return map.entrySet()
                .stream()
                .map(entry -> JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType()))
                .collect(Collectors.toList());
    }
}
