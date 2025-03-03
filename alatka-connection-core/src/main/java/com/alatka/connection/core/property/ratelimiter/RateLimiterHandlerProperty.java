package com.alatka.connection.core.property.ratelimiter;

import com.alatka.connection.core.property.core.ChannelAdapterProperty;

import javax.validation.constraints.NotNull;

/**
 * alatka.connection.flow.processors[n].handler.ratelimiter
 *
 * @author whocares
 */
public class RateLimiterHandlerProperty extends ChannelAdapterProperty {

    @NotNull
    private Integer permitsPerSecond;

    private Integer warmupPeriod;

    private Boolean blocked;

    private Integer timeout;

    public Integer getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public void setPermitsPerSecond(Integer permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public Integer getWarmupPeriod() {
        return warmupPeriod;
    }

    public void setWarmupPeriod(Integer warmupPeriod) {
        this.warmupPeriod = warmupPeriod;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
