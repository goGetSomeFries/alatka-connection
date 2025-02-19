package com.alatka.connection.core.property.core;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.definition.correlationStrategies
 *
 * @author ybliu
 */
public class CorrelationStrategyProperty extends MultiTypeSupportProperty {

    public enum Type implements MultiTypeSupportProperty.Type {

        expression(Expression.class),
        custom(Custom.class),
        header(Header.class);

        private final Class<? extends Params> clazz;

        Type(Class<? extends Params> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class<? extends Params> getClazz() {
            return clazz;
        }

    }

    @Override
    public Type valueOf(String type) {
        return Type.valueOf(type);
    }

    public static class Expression extends Params {

        @NotEmpty
        private String expression;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }

    public static class Custom extends Params {

        @NotEmpty
        private String className;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }

    public static class Header extends Params {

        @NotEmpty
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
