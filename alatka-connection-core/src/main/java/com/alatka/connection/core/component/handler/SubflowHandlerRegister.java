package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.property.core.SubflowHandlerProperty;
import com.alatka.connection.core.support.RequestReplyMessageAggregator;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.gateway.GatewayMessageHandler;
import org.springframework.messaging.Message;

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

        String className = property.getResultAggregator();
        if (className != null) {
            if (!RequestReplyMessageAggregator.class.isAssignableFrom(ClassUtil.forName(className))) {
                throw new IllegalArgumentException(className + " must be an instance of " + RequestReplyMessageAggregator.class.getName());
            }
            builder.addPropertyValue("resultAggregator", ClassUtil.newInstance(className));
        }
    }

    @Override
    protected Class<CustomGatewayMessageHandler> componentClass(SubflowHandlerProperty property) {
        return CustomGatewayMessageHandler.class;
    }

    @Override
    public Class<SubflowHandlerProperty> mappingKey() {
        return SubflowHandlerProperty.class;
    }

    public static class CustomGatewayMessageHandler extends GatewayMessageHandler {

        private RequestReplyMessageAggregator resultAggregator;

        @Override
        protected Object handleRequestMessage(Message<?> requestMessage) {
            Message<?> replyMessage = (Message<?>) super.handleRequestMessage(requestMessage);
            if (resultAggregator == null) {
                return replyMessage;
            }
            return resultAggregator.apply(requestMessage, replyMessage);
        }

        public void setResultAggregator(RequestReplyMessageAggregator resultAggregator) {
            this.resultAggregator = resultAggregator;
        }
    }
}
