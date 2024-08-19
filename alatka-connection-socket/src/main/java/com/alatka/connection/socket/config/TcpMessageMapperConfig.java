package com.alatka.connection.socket.config;


import com.alatka.connection.socket.support.PassThroughBytesMessageMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.connection.TcpMessageMapper;
import org.springframework.integration.mapping.ConvertingBytesMessageMapper;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;

import static com.alatka.connection.core.AlatkaConnectionConstant.FALLBACK_PREFIX;

@Configuration
public class TcpMessageMapperConfig {

    public static final String FALLBACK_PASSTHROUGH_TCP_MESSAGE_MAPPER = FALLBACK_PREFIX + "passThroughTcpMessageMapper";
    public static final String FALLBACK_DEFAULT_TCP_MESSAGE_MAPPER = FALLBACK_PREFIX + "defaultTcpMessageMapper";
    public static final String FALLBACK_COMPOSITE_MESSAGE_CONVERTER = FALLBACK_PREFIX + "compositeMessageConverter";

    @Bean(FALLBACK_PASSTHROUGH_TCP_MESSAGE_MAPPER)
    @ConditionalOnMissingBean(name = FALLBACK_PASSTHROUGH_TCP_MESSAGE_MAPPER)
    public TcpMessageMapper passThroughTcpMessageMapper() {
        TcpMessageMapper mapper = new TcpMessageMapper();
        mapper.setBytesMessageMapper(new PassThroughBytesMessageMapper());
        return mapper;
    }

    @Bean(FALLBACK_DEFAULT_TCP_MESSAGE_MAPPER)
    @ConditionalOnMissingBean(name = FALLBACK_DEFAULT_TCP_MESSAGE_MAPPER)
    public TcpMessageMapper defaultTcpMessageMapper() {
        TcpMessageMapper mapper = new TcpMessageMapper();
        mapper.setBytesMessageMapper(new ConvertingBytesMessageMapper(messageConverter()));
        return mapper;
    }

    @Bean(FALLBACK_COMPOSITE_MESSAGE_CONVERTER)
    @ConditionalOnMissingBean(name = FALLBACK_COMPOSITE_MESSAGE_CONVERTER)
    public ConfigurableCompositeMessageConverter messageConverter() {
        return new ConfigurableCompositeMessageConverter();
    }
}
