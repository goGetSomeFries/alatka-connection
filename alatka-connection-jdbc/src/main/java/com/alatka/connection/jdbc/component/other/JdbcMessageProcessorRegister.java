package com.alatka.connection.jdbc.component.other;

import com.alatka.connection.core.component.other.EmbeddedComponentRegister;
import com.alatka.connection.core.property.jdbc.JdbcHandlerProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.jdbc.support.JdbcMessageProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author whocares
 * @see JdbcMessageProcessor
 */
public class JdbcMessageProcessorRegister extends EmbeddedComponentRegister<JdbcHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, JdbcHandlerProperty property) {
        if (property.getJdbcTemplate() != null) {
            builder.addConstructorArgReference(property.getJdbcTemplate());
        } else {
            builder.addConstructorArgReference(property.getDataSource());
        }
        builder.addPropertyValue("sql", property.getSql());
        builder.addPropertyValue("resultClass", ClassUtil.forName(property.getResultClass()));
        builder.addPropertyValue("multiple", property.getMultiple());
    }

    @Override
    protected Class<JdbcMessageProcessor> componentClass(JdbcHandlerProperty property) {
        return JdbcMessageProcessor.class;
    }

    @Override
    protected String beanNameSuffix() {
        return "JdbcMessageProcessor";
    }
}
