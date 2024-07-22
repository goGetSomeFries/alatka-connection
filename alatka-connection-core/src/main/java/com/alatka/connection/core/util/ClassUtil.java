package com.alatka.connection.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ClassUtil {

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
}