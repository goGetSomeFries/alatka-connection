package com.alatka.connection.jdbc.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.jdbc.JdbcOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.jdbc.JdbcMessageHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class JdbcOutboundRegister extends OutboundComponentRegister<JdbcOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, JdbcOutboundProperty property) {
        if (property.getJdbcTemplate() != null) {
            builder.addConstructorArgValue(property.getJdbcTemplate());
        } else {
            builder.addConstructorArgValue(property.getDataSource());
        }
        builder.addConstructorArgValue(property.getSql());
    }

    @Override
    protected Class<JdbcMessageHandler> componentClass() {
        return JdbcMessageHandler.class;
    }

    @Override
    public Class<JdbcOutboundProperty> mappingKey() {
        return JdbcOutboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return OutboundModel.jdbc.name();
    }
}
