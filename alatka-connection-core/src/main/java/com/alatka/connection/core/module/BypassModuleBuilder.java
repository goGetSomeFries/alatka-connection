package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;

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

    @Override
    protected void buildInputChannel() {
        /**
         * 使用{@link ConnectionConstant#OUTBOUND_INPUT_CHANNEL}，无需注册bean
         */
    }

    @Override
    protected void buildOutputChannel() {
        /**
         * 使用{@link ConnectionConstant#FALLBACK_NULL_CHANNEL}，无需注册bean
         */
    }

    @Override
    protected ChannelAdapterProperty validateAndConvert(Map<OutboundModel, Object> map) {
        if (map == null) {
            return null;
        }
        return super.validateAndConvert(map);
    }

    @Override
    protected String beanNamePrefix() {
        return "bypass";
    }

    @Override
    protected String inputChannel() {
        return ConnectionConstant.BYPASS_INPUT_CHANNEL;
    }

    @Override
    protected String outputChannel() {
        return ConnectionConstant.BYPASS_OUTPUT_CHANNEL;
    }

}