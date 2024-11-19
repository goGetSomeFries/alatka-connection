package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.core.OutboundProperty;

import java.util.List;
import java.util.Map;

/**
 * bypass（旁路）模块构建器<br><br>
 * alatka.connection.bypass.http<br>
 * alatka.connection.bypass.tcp_simplex<br>
 * alatka.connection.bypass.tcp_duplex<br>
 * alatka.connection.bypass.......
 *
 * @author ybliu
 */
public class BypassModuleBuilder extends OutboundModuleBuilder {

    public BypassModuleBuilder(String identity) {
        super(identity);
    }

    /**
     * 使用{@link AlatkaConnectionConstant#OUTBOUND_INPUT_CHANNEL}，无需注册bean
     */
    @Override
    protected void buildInputChannel() {
    }

    /**
     * 使用{@link com.alatka.connection.core.config.DefaultConfig#FALLBACK_NULL_CHANNEL}，无需注册bean
     */
    @Override
    protected void buildOutputChannel() {
    }

    @Override
    protected List<OutboundProperty> validateAndConvert(Map<OutboundModel, Object> map) {
        if (map == null) {
            return null;
        }
        return super.validateAndConvert(map);
    }

    @Override
    protected String endpointName() {
        return "bypass";
    }

    @Override
    protected int getOrder() {
        return super.getOrder() + 2;
    }

    @Override
    protected String inputChannel() {
        return AlatkaConnectionConstant.BYPASS_INPUT_CHANNEL;
    }

    @Override
    protected String outputChannel() {
        return AlatkaConnectionConstant.BYPASS_OUTPUT_CHANNEL;
    }

}