package com.alatka.connection.core.util;

import java.lang.reflect.Constructor;
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
}
