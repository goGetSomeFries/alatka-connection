package com.alatka.connection.core;

import com.alatka.connection.core.config.DefaultConfig;

/**
 * 常量
 *
 * @author ybliu
 */
public final class ConnectionConstant {

    public static final String FALLBACK_PREFIX = "fallback@";

    public static final String INBOUND_INPUT_CHANNEL = "channel.inbound.input";
    public static final String INBOUND_OUTPUT_CHANNEL = "channel.inbound.output";
    public static final String OUTBOUND_INPUT_CHANNEL = "channel.outbound.input";
    public static final String OUTBOUND_OUTPUT_CHANNEL = "channel.outbound.output";
    public static final String BYPASS_INPUT_CHANNEL = OUTBOUND_INPUT_CHANNEL;
    public static final String BYPASS_OUTPUT_CHANNEL = DefaultConfig.FALLBACK_NULL_CHANNEL;
}
