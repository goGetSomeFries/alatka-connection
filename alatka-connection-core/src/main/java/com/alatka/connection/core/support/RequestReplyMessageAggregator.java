package com.alatka.connection.core.support;

import org.springframework.messaging.Message;

import java.util.function.BiFunction;

public interface RequestReplyMessageAggregator extends BiFunction<Message<?>, Message<?>, Message<?>> {
}
