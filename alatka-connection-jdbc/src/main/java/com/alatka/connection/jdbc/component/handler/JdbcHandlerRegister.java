package com.alatka.connection.jdbc.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.jdbc.support.JdbcMessageProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

import java.util.Map;

/**
 * TODO
 *
 * @author ybliu
 */
public class JdbcHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_JDBC_TEMPLATE = "jdbcTemplate";

    private static final String KEY_DATA_SOURCE = "dataSource";

    private static final String KEY_RESULT_CLASS = "resultClass";

    private static final String KEY_SQL = "sql";

    private static final String KEY_MULTIPLE = "multiple";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String beanName = this.registerMessageProcessor(property.getId(), params);
        builder.addConstructorArgReference(beanName);
    }

    private String registerMessageProcessor(String id, Map<String, Object> params) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(JdbcMessageProcessor.class);
        if (getParamsValue(params, KEY_JDBC_TEMPLATE) != null) {
            builder.addConstructorArgReference(getParamsValue(params, KEY_JDBC_TEMPLATE));
        } else {
            builder.addConstructorArgReference(getParamsValueOrThrow(params, KEY_DATA_SOURCE));
        }
        builder.addPropertyValue("sql", getParamsValueOrThrow(params, KEY_SQL));
        builder.addPropertyValue("resultClass", ClassUtil.forName(getParamsValueOrThrow(params, KEY_RESULT_CLASS)));
        builder.addPropertyValue("multiple", getParamsValueOrThrow(params, KEY_MULTIPLE));

        String beanName = id + ".messageProcessor";
        getBeanFactory().registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.jdbc;
    }
}
