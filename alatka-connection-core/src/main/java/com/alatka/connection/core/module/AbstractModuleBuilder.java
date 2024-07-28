package com.alatka.connection.core.module;

import com.alatka.connection.core.annotation.BeanProperty;
import com.alatka.connection.core.annotation.ReferenceBeanProperty;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.ReferenceProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @param <T>
 * @param <S>
 */
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String identity;

    public AbstractModuleBuilder(String identity) {
        this.identity = identity;
    }

    @Override
    public final void build(T model) {
        if (model != null) {
            Validator.validateByException(model);
        }

        S convertedModel = this.validateAndConvert(model);
        if (convertedModel != null) {
            Map<Object, ? extends ComponentRegister> mapping = this.mappingComponentRegister();

            this.preDoBuild(convertedModel);
            this.doBuild(convertedModel, mapping);
        }
    }

    private Map<Object, ? extends ComponentRegister> mappingComponentRegister() {
        if (this.componentRegisterClass() == null) {
            return Collections.emptyMap();
        }
        List<? extends ComponentRegister> list = SpringFactoriesLoader.loadFactories(this.componentRegisterClass(), null);
        if (list.isEmpty()) {
            list.add(ClassUtil.newInstance(this.componentRegisterClass().getName()));
        }
        return list.stream().collect(Collectors.toMap(ReferenceProperty::reference, Function.identity()));
    }

    protected S validateAndConvert(T model) {
        return (S) model;
    }

    private void preDoBuild(Object model) {
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
                        if (!value.contains("@")) {
                            String lastValue = this.identity.concat("@").concat(value);
                            ClassUtil.setValue(field, property, lastValue);
                        }
                    }
                });

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            this.assignIdentity(property, superclass);
        }
    }

    /**
     * 基于SpringFactories实现<br>
     * 在{@link SpringFactoriesLoader#FACTORIES_RESOURCE_LOCATION}中配置{@link ComponentRegister} {@link Class}类型
     *
     * @return {@link ComponentRegister} {@link Class}类型
     */
    protected abstract Class<? extends ComponentRegister> componentRegisterClass();

    /**
     * @param model
     * @param mapping
     */
    protected abstract void doBuild(S model, Map<Object, ? extends ComponentRegister> mapping);

}
