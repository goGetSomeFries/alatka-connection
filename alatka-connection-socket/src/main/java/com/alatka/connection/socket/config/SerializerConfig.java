package com.alatka.connection.socket.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.serializer.*;

@Configuration
public class SerializerConfig {

    /**
     * 分隔符：'/n'
     */
    @Bean
    @ConditionalOnMissingBean
    public ByteArrayLfSerializer byteArrayLfSerializer() {
        return ByteArrayLfSerializer.INSTANCE;
    }

    /**
     * 分隔符：'/r/n'
     */
    @Bean
    @ConditionalOnMissingBean
    public ByteArrayCrLfSerializer byteArrayCrLfSerializer() {
        return ByteArrayCrLfSerializer.INSTANCE;
    }

    /**
     * STX + payload + ETX
     */
    @Bean
    @ConditionalOnMissingBean
    public ByteArrayStxEtxSerializer byteArrayStxEtxSerializer() {
        return ByteArrayStxEtxSerializer.INSTANCE;
    }

    /**
     * 透传
     */
    @Bean
    @ConditionalOnMissingBean
    public ByteArrayRawSerializer byteArrayRawSerializer() {
        return ByteArrayRawSerializer.INSTANCE;
    }

    /**
     * 4字节消息头
     */
    @Bean
    @ConditionalOnMissingBean(name = "byteArray4LengthHeaderSerializer")
    public ByteArrayLengthHeaderSerializer byteArray4LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_INT).inclusive();
    }

    /**
     * 2字节消息头
     */
    @Bean
    @ConditionalOnMissingBean(name = "byteArray2LengthHeaderSerializer")
    public ByteArrayLengthHeaderSerializer byteArray2LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_UNSIGNED_SHORT).inclusive();
    }

    /**
     * 1字节消息头
     */
    @Bean
    @ConditionalOnMissingBean(name = "byteArray1LengthHeaderSerializer")
    public ByteArrayLengthHeaderSerializer byteArray1LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_UNSIGNED_BYTE).inclusive();
    }

}
