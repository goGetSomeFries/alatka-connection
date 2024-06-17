package com.alatka.connection.socket;

import com.alatka.connection.socket.component.support.ByteArraySingleTerminatorSerializerRegister;
import com.alatka.connection.socket.config.SerializerConfig;
import com.alatka.connection.socket.config.TcpMessageMapperConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybliu
 */
@Configuration
@ImportAutoConfiguration({
        SerializerConfig.class,
        TcpMessageMapperConfig.class})
public class AutoConfiguration {

    @Bean
    public ByteArraySingleTerminatorSerializerRegister byteArraySingleTerminatorSerializerRegister() {
        return new ByteArraySingleTerminatorSerializerRegister();
    }
}
