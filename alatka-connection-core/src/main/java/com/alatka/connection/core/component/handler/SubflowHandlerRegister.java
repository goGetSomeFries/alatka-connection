package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.property.core.SubflowHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.gateway.GatewayMessageHandler;

import java.util.Optional;

/**
 * 子流程组件注册器
 *
 * @author ybliu
 * @see GatewayMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#subflow
 */
public class SubflowHandlerRegister extends HandlerComponentRegister<SubflowHandlerProperty> {

    @Override
    public void doRegister(BeanDefinitionBuilder builder, SubflowHandlerProperty property) {
        String requestChannel = property.getRequestChannel() == null ?
                AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL : property.getRequestChannel();
        builder.addPropertyValue("requestChannelName",
                property.getIdentity() + AlatkaConnectionConstant.IDENTITY_SEPARATOR + requestChannel);

        String replyChannel = property.getReplyChannel() == null ?
                AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL : property.getReplyChannel();
        builder.addPropertyValue("replyChannelName",
                property.getIdentity() + AlatkaConnectionConstant.IDENTITY_SEPARATOR + replyChannel);

        String errorChannel = property.getErrorChannel() == null ?
                AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL : property.getErrorChannel();
        builder.addPropertyValue("errorChannelName",
                property.getIdentity() + AlatkaConnectionConstant.IDENTITY_SEPARATOR + errorChannel);

        builder.addPropertyValue("requestTimeout", Optional.ofNullable(property.getRequestTimeout()).orElse(-1L));
        builder.addPropertyValue("replyTimeout", Optional.ofNullable(property.getReplyTimeout()).orElse(-1L));

    }

    @Override
    protected Class<GatewayMessageHandler> componentClass(SubflowHandlerProperty property) {
        return GatewayMessageHandler.class;
    }

    @Override
    public Class<SubflowHandlerProperty> mappingKey() {
        return SubflowHandlerProperty.class;
    }
}
