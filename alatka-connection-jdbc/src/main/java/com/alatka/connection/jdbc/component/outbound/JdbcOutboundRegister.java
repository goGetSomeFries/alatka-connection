package com.alatka.connection.jdbc.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.jdbc.JdbcOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.jdbc.JdbcMessageHandler;

/**
 * jdbc组件注册器
 *
 * @author ybliu
 * @see JdbcMessageHandler
 * @see com.alatka.connection.core.model.OutboundModel#jdbc
 */
public class JdbcOutboundRegister extends OutboundComponentRegister<JdbcOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, JdbcOutboundProperty property) {
        if (property.getJdbcTemplate() != null) {
            builder.addConstructorArgReference(property.getJdbcTemplate());
        } else {
            builder.addConstructorArgReference(property.getDataSource());
        }
        builder.addConstructorArgValue(property.getSql());
    }

    @Override
    protected Class<JdbcMessageHandler> componentClass(JdbcOutboundProperty property) {
        return JdbcMessageHandler.class;
    }

    @Override
    public Class<JdbcOutboundProperty> mappingKey() {
        return JdbcOutboundProperty.class;
    }

}
