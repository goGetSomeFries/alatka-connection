package com.alatka.connection.core.property.support;

import com.alatka.connection.core.property.Property;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author ybliu
 */
public class PollerMetadataProperty extends Property {

    @NotNull
    private String taskExecutor;
    @NotNull
    private Long maxMessagesPerPoll;
    @NotEmpty
    @Size(min = 1, max = 1)
    private Map<Trigger.Type, Object> trigger;

    public interface Trigger {
        enum Type {
            periodic(Periodic.class), cron(Cron.class);

            private Class<? extends Trigger> clazz;

            Type(Class<? extends Trigger> clazz) {
                this.clazz = clazz;
            }

            public Class<? extends Trigger> getClazz() {
                return clazz;
            }
        }
    }

    public class Periodic implements Trigger {
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

    public class Cron implements Trigger {
        @NotBlank
        private String expression;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }

    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Long getMaxMessagesPerPoll() {
        return maxMessagesPerPoll;
    }

    public void setMaxMessagesPerPoll(Long maxMessagesPerPoll) {
        this.maxMessagesPerPoll = maxMessagesPerPoll;
    }

    public Map<Trigger.Type, Object> getTrigger() {
        return trigger;
    }

    public void setTrigger(Map<Trigger.Type, Object> trigger) {
        this.trigger = trigger;
    }
}