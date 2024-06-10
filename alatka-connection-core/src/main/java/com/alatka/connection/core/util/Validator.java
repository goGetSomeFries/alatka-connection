package com.alatka.connection.core.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Validator {

    public static javax.validation.Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

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
