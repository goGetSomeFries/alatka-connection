package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.annotation.IdentityPropertyReference;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.core.Property;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.core.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link ModuleBuilder}抽象类
 *
 * @param <T> model实体类型
 * @param <S> 转换后{@link Property}或者{@link List<Property>}
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractModuleBuilder<T, S> implements ModuleBuilder<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * alatka-connection标识
     */
    private final String identity;

    public AbstractModuleBuilder(String identity) {
        this.identity = identity;
    }

    @Override
    public final void build(T model) {
        if (model != null) {
            Validator.validateByException(model);
        }

        S property = this.validateAndConvert(model);
        if (property != null) {
            Map<Object, ? extends ComponentRegister> mapping = this.mappingComponentRegister();

            this.preDoBuild(property);
            this.doBuild(property, mapping);
        }
    }

    /**
     * 基于SpringFactories实现<br>
     * 在{@link SpringFactoriesLoader#FACTORIES_RESOURCE_LOCATION}中配置{@link ComponentRegister} {@link Class}类型
     *
     * @return {@link ComponentRegister}对象映射
     */
    private Map<Object, ? extends ComponentRegister> mappingComponentRegister() {
        if (this.componentRegisterClass() == null) {
            return Collections.emptyMap();
        }
        List<? extends ComponentRegister> list = SpringFactoriesLoader.loadFactories(this.componentRegisterClass(), null);
        return list.stream().collect(Collectors.toMap(ComponentRegister::mappingKey, Function.identity()));
    }

    protected void preDoBuild(Object object) {
        List<? extends Property> list =
                (List<? extends Property>) (object instanceof List ? object : Collections.singletonList(object));
        list.forEach(property -> this.assignIdentity(property, property.getClass()));
    }

    /**
     * 为注解{@link IdentityProperty}字段增加前缀{@link #identity}
     *
     * @param property {@link Property}对象
     * @param clazz    {{@link Property}类型
     */
    private void assignIdentity(Object property, Class<?> clazz) {
        Stream.of(clazz.getDeclaredFields())
                .peek(field -> {
                    if (field.isAnnotationPresent(IdentityPropertyReference.class)) {
                        Object value = ClassUtil.getValue(field, property);
                        if (value instanceof Collection) {
                            Class<?> collectionClass = ClassUtil.getGenericType(field);
                            ((Collection<?>) value).forEach(v -> this.assignIdentity(v, collectionClass));
                        } else {
                            this.assignIdentity(value, value.getClass());
                        }
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
        return Optional.ofNullable(mapping.get(key))
                .orElseThrow(() -> new IllegalArgumentException("未映射对应ComponentRegister, key: " + key + ", mapping: " + mapping));
    }

    /**
     * 校验并转换为{@link Property}
     *
     * @param model {@link #build(Object)}
     * @return {@link Property}或者{@link List<Property>}
     */
    protected abstract S validateAndConvert(T model);

    /**
     * 基于SpringFactories实现<br>
     * 在{@link SpringFactoriesLoader#FACTORIES_RESOURCE_LOCATION}中配置{@link ComponentRegister} {@link Class}类型
     *
     * @return {@link ComponentRegister} {@link Class}类型
     */
    protected abstract Class<? extends ComponentRegister> componentRegisterClass();

    /**
     * 构建Component
     *
     * @param model   转换后{@link Property}或者{@link List<Property>}
     * @param mapping {@link #getComponentRegister(Object, Map)}
     */
    protected abstract void doBuild(S model, Map<Object, ? extends ComponentRegister> mapping);

}
