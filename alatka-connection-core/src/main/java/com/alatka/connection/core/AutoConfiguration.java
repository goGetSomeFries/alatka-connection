package com.alatka.connection.core;

import com.alatka.connection.core.config.DefaultConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author ybliu
 */
@Configuration
@ImportAutoConfiguration({DefaultConfig.class})
public class AutoConfiguration {
}
