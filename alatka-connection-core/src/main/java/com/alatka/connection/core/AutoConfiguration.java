package com.alatka.connection.core;

import com.alatka.connection.core.config.ConnectionFallbackConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybliu
 */
@Configuration
@ImportAutoConfiguration({ConnectionFallbackConfig.class})
public class AutoConfiguration {
}
