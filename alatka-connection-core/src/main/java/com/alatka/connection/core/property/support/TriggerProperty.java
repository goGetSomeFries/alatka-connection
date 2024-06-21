package com.alatka.connection.core.property.support;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 不需要{@link com.alatka.connection.core.component.ComponentRegister}单独解析，无需继承{@link com.alatka.connection.core.property.Property}
 *
 * @author ybliu
 */
public class TriggerProperty {

    @NotNull
    private Type type;
    @NotNull
    private Object params;

    public enum Type {
        periodic(PeriodicProperty.class), cron(CronProperty.class);

        private Class<?> clazz;

        Type(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Class<?> getClazz() {
            return clazz;
        }
    }

    public class PeriodicProperty {
        @NotNull
        private Long period;
        private boolean fixedRate = false;
        private Long initialDelay = 0L;

        public Long getPeriod() {
            return period;
        }

        public void setPeriod(Long period) {
            this.period = period;
        }

        public boolean getFixedRate() {
            return fixedRate;
        }

        public void setFixedRate(boolean fixedRate) {
            this.fixedRate = fixedRate;
        }

        public Long getInitialDelay() {
            return initialDelay;
        }

        public void setInitialDelay(Long initialDelay) {
            this.initialDelay = initialDelay;
        }
    }

    public class CronProperty {
        @NotBlank
        private String expression;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
