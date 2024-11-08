package com.alatka.connection.jdbc.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomHandler;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.jdbc.support.JdbcHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
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

        JdbcHandler instance;
        if (params.get(KEY_JDBC_TEMPLATE) != null) {
            JdbcTemplate jdbcTemplate =
                    this.getBeanFactory().getBean(params.get(KEY_JDBC_TEMPLATE).toString(), JdbcTemplate.class);
            instance = new JdbcHandler(jdbcTemplate);
        } else {
            DataSource dataSource =
                    this.getBeanFactory().getBean(params.get(KEY_DATA_SOURCE).toString(), DataSource.class);
            instance = new JdbcHandler(dataSource);
        }
        instance.setSql(params.get(KEY_SQL).toString());
        instance.setResultClass(ClassUtil.forName(params.get(KEY_RESULT_CLASS).toString()));
        instance.setMultiple((boolean) params.get(KEY_MULTIPLE));

        builder.addConstructorArgValue(instance)
                .addConstructorArgValue(CustomHandler.METHOD_NAME);
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
