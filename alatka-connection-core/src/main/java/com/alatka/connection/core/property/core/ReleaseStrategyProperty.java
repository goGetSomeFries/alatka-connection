package com.alatka.connection.core.property.core;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.definition.releaseStrategies
 *
 * @author ybliu
 * @see org.springframework.integration.aggregator.ReleaseStrategy
 */
public class ReleaseStrategyProperty extends MultiTypeSupportProperty {

    public enum Type implements MultiTypeSupportProperty.Type {

        expression(Expression.class),
        custom(Custom.class),
        messageCount(MessageCount.class);

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

    public static class MessageCount extends Params {

        @NotEmpty
        private Integer threshold;

        public Integer getThreshold() {
            return threshold;
        }

        public void setThreshold(Integer threshold) {
            this.threshold = threshold;
        }
    }
}
