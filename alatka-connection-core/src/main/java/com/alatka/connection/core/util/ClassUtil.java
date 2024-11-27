package com.alatka.connection.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public class ClassUtil {

    public static <T> T newInstance(String className) {
        return newInstance(className, null, null);
    }

    public static <T> T newInstance(String className, Class<?>[] paramTypes, Object[] params) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes == null ? new Class<?>[0] : paramTypes);
            return (T) constructor.newInstance(params == null ? new Object[0] : params);
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException("");
        }
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <R> R getValue(Field field, Object instance) {
        try {
            field.setAccessible(true);
            return (R) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setValue(Field field, Object instance, Object value) {
        try {
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取集合泛型类型
     *
     * @param field {@link Field}
     * @return 集合泛型类型
     */
    public static Class<?> getGenericType(Field field) {
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        return (Class<?>) type.getActualTypeArguments()[0];
    }
}
