package com.alatka.connection.jdbc.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.JdbcHandlerProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.jdbc.support.JdbcMessageProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class JdbcHandlerRegister extends HandlerComponentRegister<JdbcHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, JdbcHandlerProperty property) {
        String beanName = this.registerMessageProcessor(property.getId(), property);
        builder.addConstructorArgReference(beanName);
    }

    private String registerMessageProcessor(String id, JdbcHandlerProperty property) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(JdbcMessageProcessor.class);
        if (property.getJdbcTemplate() != null) {
            builder.addConstructorArgReference(property.getJdbcTemplate());
        } else {
            builder.addConstructorArgReference(property.getDataSource());
        }
        builder.addPropertyValue("sql", property.getSql());
        builder.addPropertyValue("resultClass", ClassUtil.forName(property.getResultClass()));
        builder.addPropertyValue("multiple", property.getMultiple());

        String beanName = id + ".messageProcessor";
        getBeanFactory().registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public Class<JdbcHandlerProperty> mappingKey() {
        return JdbcHandlerProperty.class;
    }
}
