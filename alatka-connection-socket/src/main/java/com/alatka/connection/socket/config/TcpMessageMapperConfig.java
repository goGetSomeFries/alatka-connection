package com.alatka.connection.socket.config;


import com.alatka.connection.socket.support.PassThroughBytesMessageMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.connection.TcpMessageMapper;

@Configuration
public class TcpMessageMapperConfig {

    @Bean
    public TcpMessageMapper passThroughTcpMessageMapper() {
        TcpMessageMapper mapper = new TcpMessageMapper();
        mapper.setBytesMessageMapper(new PassThroughBytesMessageMapper());
        return mapper;
    }

}
