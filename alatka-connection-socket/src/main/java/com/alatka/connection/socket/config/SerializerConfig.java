package com.alatka.connection.socket.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.serializer.*;

import static com.alatka.connection.core.ConnectionConstant.FALLBACK_PREFIX;

@Configuration
public class SerializerConfig {

    public static final String FALLBACK_BYTES_LF_SERIALIZER = FALLBACK_PREFIX + "byteArrayLfSerializer";

    public static final String FALLBACK_BYTES_CRLF_SERIALIZER = FALLBACK_PREFIX + "byteArrayCrLfSerializer";

    public static final String FALLBACK_BYTES_STX_ETX_SERIALIZER = FALLBACK_PREFIX + "byteArrayStxEtxSerializer";

    public static final String FALLBACK_BYTES_RAW_SERIALIZER = FALLBACK_PREFIX + "byteArrayRawSerializer";

    public static final String FALLBACK_BYTES_4_LENGTH_HEADER_SERIALIZER = FALLBACK_PREFIX + "byteArray4LengthHeaderSerializer";

    public static final String FALLBACK_BYTES_2_LENGTH_HEADER_SERIALIZER = FALLBACK_PREFIX + "byteArray2LengthHeaderSerializer";

    public static final String FALLBACK_BYTES_1_LENGTH_HEADER_SERIALIZER = FALLBACK_PREFIX + "byteArray1LengthHeaderSerializer";

    /**
     * 分隔符：'/n'
     */
    @Bean(FALLBACK_BYTES_LF_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_LF_SERIALIZER)
    public ByteArrayLfSerializer byteArrayLfSerializer() {
        return ByteArrayLfSerializer.INSTANCE;
    }

    /**
     * 分隔符：'/r/n'
     */
    @Bean(FALLBACK_BYTES_CRLF_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_CRLF_SERIALIZER)
    public ByteArrayCrLfSerializer byteArrayCrLfSerializer() {
        return ByteArrayCrLfSerializer.INSTANCE;
    }

    /**
     * STX + payload + ETX
     */
    @Bean(FALLBACK_BYTES_STX_ETX_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_STX_ETX_SERIALIZER)
    public ByteArrayStxEtxSerializer byteArrayStxEtxSerializer() {
        return ByteArrayStxEtxSerializer.INSTANCE;
    }

    /**
     * 透传
     */
    @Bean(FALLBACK_BYTES_RAW_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_RAW_SERIALIZER)
    public ByteArrayRawSerializer byteArrayRawSerializer() {
        return ByteArrayRawSerializer.INSTANCE;
    }

    /**
     * 4字节消息头
     */
    @Bean(FALLBACK_BYTES_4_LENGTH_HEADER_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_4_LENGTH_HEADER_SERIALIZER)
    public ByteArrayLengthHeaderSerializer byteArray4LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_INT).inclusive();
    }

    /**
     * 2字节消息头
     */
    @Bean(FALLBACK_BYTES_2_LENGTH_HEADER_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_2_LENGTH_HEADER_SERIALIZER)
    public ByteArrayLengthHeaderSerializer byteArray2LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_UNSIGNED_SHORT).inclusive();
    }

    /**
     * 1字节消息头
     */
    @Bean(FALLBACK_BYTES_1_LENGTH_HEADER_SERIALIZER)
    @ConditionalOnMissingBean(name = FALLBACK_BYTES_1_LENGTH_HEADER_SERIALIZER)
    public ByteArrayLengthHeaderSerializer byteArray1LengthHeaderSerializer() {
        return new ByteArrayLengthHeaderSerializer(ByteArrayLengthHeaderSerializer.HEADER_SIZE_UNSIGNED_BYTE).inclusive();
    }

}
