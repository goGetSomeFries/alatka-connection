package com.alatka.connection.socket.config;


import com.alatka.connection.socket.support.PassThroughBytesMessageMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.connection.TcpMessageMapper;

import static com.alatka.connection.core.AlatkaConnectionConstant.FALLBACK_PREFIX;

@Configuration
public class TcpMessageMapperConfig {

    public static final String FALLBACK_PASSTHROUGH_TCP_MESSAGE_MAPPER = FALLBACK_PREFIX + "passThroughTcpMessageMapper";

    @Bean(FALLBACK_PASSTHROUGH_TCP_MESSAGE_MAPPER)
    public TcpMessageMapper passThroughTcpMessageMapper() {
        TcpMessageMapper mapper = new TcpMessageMapper();
        mapper.setBytesMessageMapper(new PassThroughBytesMessageMapper());
        return mapper;
    }

}
