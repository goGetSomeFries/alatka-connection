package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.core.GatewayInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * gateway组件注册器
 *
 * @author ybliu
 */
public class GatewayInboundRegister extends InboundComponentRegister<GatewayInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, GatewayInboundProperty property) {
        // do nothing
    }

    @Override
    protected Class<Void> componentClass(GatewayInboundProperty property) {
        return Void.class;
    }

    @Override
    public Class<GatewayInboundProperty> mappingKey() {
        return GatewayInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.gateway.name();
    }
}
