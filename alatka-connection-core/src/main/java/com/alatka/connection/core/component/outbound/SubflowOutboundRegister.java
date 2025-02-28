package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.handler.SubflowHandlerRegister;
import com.alatka.connection.core.property.core.SubflowOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.gateway.GatewayMessageHandler;

/**
 * 子流程组件注册器
 *
 * @author ybliu
 * @see SubflowHandlerRegister
 * @see com.alatka.connection.core.model.OutboundModel#subflow
 */
public class SubflowOutboundRegister extends OutboundComponentRegister<SubflowOutboundProperty> {

    private final SubflowHandlerRegister handlerRegister = new SubflowHandlerRegister();

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, SubflowOutboundProperty property) {
        this.handlerRegister.doRegister(builder, property.getSubflow());
    }

    @Override
    protected Class<GatewayMessageHandler> componentClass(SubflowOutboundProperty property) {
        return GatewayMessageHandler.class;
    }

    @Override
    public Class<SubflowOutboundProperty> mappingKey() {
        return SubflowOutboundProperty.class;
    }

}
