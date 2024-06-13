package com.alatka.connection.socket;

import com.alatka.connection.socket.config.SerializerConfig;
import com.alatka.connection.socket.config.TcpMessageMapperConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybliu
 */
@Configuration
@ImportAutoConfiguration({
        SerializerConfig.class,
        TcpMessageMapperConfig.class})
public class AutoConfiguration {
}
