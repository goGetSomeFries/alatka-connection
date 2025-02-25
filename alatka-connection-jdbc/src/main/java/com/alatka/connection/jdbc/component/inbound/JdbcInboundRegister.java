package com.alatka.connection.jdbc.component.inbound;

import com.alatka.connection.core.component.inbound.SourcePollingInboundRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.jdbc.JdbcInboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * {@link JdbcPollingChannelAdapter}组件注册器
 *
 * @author ybliu
 * @see JdbcPollingChannelAdapter
 */
public class JdbcInboundRegister extends SourcePollingInboundRegister<JdbcInboundProperty> {

    @Override
    protected void doRegisterMessageSource(BeanDefinitionBuilder builder, JdbcInboundProperty property) {
        if (property.getJdbcTemplate() != null) {
            builder.addConstructorArgReference(property.getJdbcTemplate());
        } else {
            builder.addConstructorArgReference(property.getDataSource());
        }
        builder.addConstructorArgValue(property.getSelectSql());
        builder.addPropertyValue("maxRows", property.getMaxRows());
        builder.addPropertyValue("updatePerRow", property.isUpdatePerRow());
        if (property.getUpdateSql() != null) {
            builder.addPropertyValue("updateSql", property.getUpdateSql());
        }
        builder.addPropertyValue("selectSqlParameterSource", new MapSqlParameterSource(property.getParams()));
        builder.addPropertyValue("rowMapper", new BeanPropertyRowMapper<>(ClassUtil.forName(property.getResultClass())));
    }

    @Override
    protected Class<JdbcPollingChannelAdapter> messageSourceClass() {
        return JdbcPollingChannelAdapter.class;
    }

    @Override
    public Class<JdbcInboundProperty> mappingKey() {
        return JdbcInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.jdbc.name();
    }
}
