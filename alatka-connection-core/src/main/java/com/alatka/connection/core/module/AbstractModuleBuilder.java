package com.alatka.connection.core.module;

import com.alatka.connection.core.annotation.BeanProperty;
import com.alatka.connection.core.annotation.ReferenceBeanProperty;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.ReferenceProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.core.util.Validator;
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
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    private String identity;

    public AbstractModuleBuilder(String identity) {
        this.identity = identity;
    }

    @Override
    public final void build(T model) {
        if (model != null) {
            Validator.validateByException(model);
        }

        Map<Object, ComponentRegister> mapping = this.registerMapping();
        S convertedModel = this.convert(model);
        this.postConvert(convertedModel);

        this.doBuild(convertedModel, mapping);
    }

    private Map<Object, ComponentRegister> registerMapping() {
        return SpringFactoriesLoader.loadFactories(this.componentRegisterClass(), null).stream()
                .collect(Collectors.toMap(ReferenceProperty::reference, Function.identity()));
    }

    protected S convert(T model) {
        return (S) model;
    }

    private void postConvert(Object model) {
        List<?> list = model instanceof List ? (List<?>) model : Collections.singletonList(model);
        list.stream().forEach(instance -> this.assignIdentity(instance, instance.getClass()));
    }

    private void assignIdentity(Object instance, Class<?> clazz) {
        Stream.of(clazz.getDeclaredFields())
                .peek(field -> {
                    if (field.isAnnotationPresent(ReferenceBeanProperty.class)) {
                        Object value = ClassUtil.getValue(field, instance);
                        this.assignIdentity(value, instance.getClass());
                    }
                })
                .forEach(field -> {
                    if (field.isAnnotationPresent(BeanProperty.class)) {
                        String value = ClassUtil.getValue(field, instance);
                        if (value != null && !value.isEmpty() && !value.contains(".")) {
                            ClassUtil.setValue(field, instance, this.identity.concat(".").concat(value));
                        }
                    }
                });

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            this.assignIdentity(instance, superclass);
        }
    }

    protected abstract Class<? extends ComponentRegister> componentRegisterClass();

    protected abstract void doBuild(S model, Map<Object, ComponentRegister> mapping);

}
