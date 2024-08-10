package com.alatka.connection.core.module;

import com.alatka.connection.core.property.core.Property;

import java.util.List;

/**
 * inbound/outbound父类
 *
 * @param <T> model实体类型
 * @param <S> 转换后{@link Property}或者{@link List <Property>}
 * @author ybliu
 * @see InboundModuleBuilder
 * @see OutboundModuleBuilder
 */
public abstract class EndpointModuleBuilder<T, S> extends AbstractModuleBuilder<T, S> {

    private boolean duplex;

    protected final ChannelModuleBuilder channelModuleBuilder;

    public EndpointModuleBuilder(String identity) {
        super(identity);
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
    }

    @Override
    protected void preDoBuild(Object model) {
        super.preDoBuild(model);

        this.buildInputChannel();
        this.buildOutputChannel();
    }

    /**
     * endpoint名称<br>
     * inbound/outbound/bypass
     *
     * @return endpoint名称
     */
    protected abstract String endpointName();

    /**
     * 构建inputChannel
     */
    protected abstract void buildInputChannel();

    /**
     * 构建outputChannel
     */
    protected abstract void buildOutputChannel();

    /**
     * 是否双工
     */
    protected boolean isDuplex() {
        return duplex;
    }

    protected void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }
}
