package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.annotation.ReferenceIdentityProperty;
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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link ModuleBuilder}抽象类
 *
 * @param <T>
 * @param <S>
 * @author ybliu
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
        return list.stream().collect(Collectors.toMap(ReferenceProperty::mappingKey, Function.identity()));
    }

    protected S validateAndConvert(T model) {
        return (S) model;
    }

    protected void preDoBuild(Object model) {
        List<? extends Property> list = (List<? extends Property>) (model instanceof List ? model : Collections.singletonList(model));
        list.stream().forEach(property -> this.assignIdentity(property, property.getClass()));
    }

    private void assignIdentity(Object property, Class<?> clazz) {
        Stream.of(clazz.getDeclaredFields())
                .peek(field -> {
                    if (field.isAnnotationPresent(ReferenceIdentityProperty.class)) {
                        Object value = ClassUtil.getValue(field, property);
                        this.assignIdentity(value, property.getClass());
                    }
                })
                .forEach(field -> {
                    if (field.isAnnotationPresent(IdentityProperty.class)) {
                        IdentityProperty annotation = field.getAnnotation(IdentityProperty.class);
                        boolean referenced = annotation.referenced();
                        String value = ClassUtil.getValue(field, property);
                        if (referenced && value != null && !value.contains(AlatkaConnectionConstant.IDENTITY_SEPARATOR)) {
                            String lastValue = this.identity.concat(AlatkaConnectionConstant.IDENTITY_SEPARATOR).concat(value);
                            ClassUtil.setValue(field, property, lastValue);
                        } else if (!referenced && (value == null || !value.contains(AlatkaConnectionConstant.IDENTITY_SEPARATOR))) {
                            String lastValue = this.identity.concat(AlatkaConnectionConstant.IDENTITY_SEPARATOR).concat(value == null ? "" : value);
                            ClassUtil.setValue(field, property, lastValue);
                        }
                    }
                });

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            this.assignIdentity(property, superclass);
        }
    }

    protected ComponentRegister getComponentRegister(Object key, Map<Object, ? extends ComponentRegister> mapping) {
        if (!mapping.containsKey(key)) {
            throw new IllegalArgumentException("未映射对应ComponentRegister, key: " + key + ", mapping: " + mapping);
        }
        return mapping.get(key);
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
