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

    protected final ChannelModuleBuilder channelModuleBuilder;

    protected final HandlerModuleBuilder handlerModuleBuilder;

    protected final ConsumerModuleBuilder consumerModuleBuilder;

    public EndpointModuleBuilder(String identity) {
        super(identity);
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
        this.handlerModuleBuilder = new HandlerModuleBuilder(identity);
        this.consumerModuleBuilder = new ConsumerModuleBuilder(identity);
    }

    @Override
    protected void preDoBuild(S property) {
        super.preDoBuild(property);

        this.buildInputChannel(property);
        this.buildOutputChannel(property);
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
    protected abstract void buildInputChannel(S property);

    /**
     * 构建outputChannel
     */
    protected abstract void buildOutputChannel(S property);

}
