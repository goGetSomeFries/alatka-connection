package com.alatka.connection.core.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class Validator {

    public static jakarta.validation.Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static String validate(Object object) {
        Set<ConstraintViolation<Object>> set = VALIDATOR.validate(object);
        AtomicInteger index = new AtomicInteger(1);
        String message = set.stream()
                .map(e -> index.getAndIncrement() + ": '" + e.getPropertyPath().toString() + "' " + e.getMessage() + ", 当前值: " + e.getInvalidValue())
                .collect(Collectors.joining("; "));
        return set.isEmpty() ? null : message;
    }

    public static void validateByException(Object object) {
        String message = validate(object);
        if (message != null) {
            throw new ValidationException(message);
        }
    }

}
