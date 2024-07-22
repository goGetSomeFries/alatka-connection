package com.alatka.connection.core.module;

import com.alatka.connection.core.annotation.BeanProperty;
import com.alatka.connection.core.annotation.ReferenceBeanProperty;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.ReferenceProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.core.util.Validator;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    private final String identity;

    public AbstractModuleBuilder(String identity) {
        this.identity = identity;
    }

    @Override
    public final List<String> build(T model) {
        if (model != null) {
            Validator.validateByException(model);
        }

        Map<Object, ComponentRegister<? extends Property, Object>> mapping = this.mappingComponentRegister();
        S convertedModel = this.convert(model);
        this.postConvert(convertedModel);

        return this.doBuild(convertedModel, mapping);
    }

    private Map<Object, ComponentRegister<? extends Property, Object>> mappingComponentRegister() {
        return SpringFactoriesLoader.loadFactories(this.componentRegisterClass(), null).stream()
                .collect(Collectors.toMap(ReferenceProperty::reference, Function.identity()));
    }

    protected S convert(T model) {
        return (S) model;
    }

    private void postConvert(Object model) {
        List<? extends Property> list = (List<? extends Property>) (model instanceof List ? model : Collections.singletonList(model));
        list.stream().forEach(property -> this.assignIdentity(property, property.getClass()));
    }

    private void assignIdentity(Object property, Class<?> clazz) {
        Stream.of(clazz.getDeclaredFields())
                .peek(field -> {
                    if (field.isAnnotationPresent(ReferenceBeanProperty.class)) {
                        Object value = ClassUtil.getValue(field, property);
                        this.assignIdentity(value, property.getClass());
                    }
                })
                .forEach(field -> {
                    if (field.isAnnotationPresent(BeanProperty.class)) {
                        String value = ClassUtil.getValue(field, property);
                        value = Optional.ofNullable(value).orElse("");
                        if (!value.contains(".")) {
                            ClassUtil.setValue(field, property, this.identity.concat(".").concat(value));
                        }
                    }
                });

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            this.assignIdentity(property, superclass);
        }
    }

    /**
     * @return
     */
    protected abstract Class<? extends ComponentRegister> componentRegisterClass();

    /**
     * @param model
     * @param mapping
     * @return
     */
    protected abstract List<String> doBuild(S model, Map<Object, ComponentRegister<? extends Property, Object>> mapping);

}
