package com.alatka.connection.jdbc.component.inbound;

import com.alatka.connection.core.component.inbound.SourcePollingInboundRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.jdbc.JdbcInboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author ybliu
 */
public class JdbcInboundRegister extends SourcePollingInboundRegister<JdbcInboundProperty> {

    @Override
    protected MessageSource messageSource(JdbcInboundProperty property) {
        JdbcPollingChannelAdapter messageSource;
        if (property.getJdbcTemplate() != null) {
            JdbcTemplate jdbcTemplate = this.getBeanFactory().getBean(property.getJdbcTemplate(), JdbcTemplate.class);
            messageSource = new JdbcPollingChannelAdapter(jdbcTemplate, property.getSelectSql());
        } else {
            DataSource dataSource = this.getBeanFactory().getBean(property.getDataSource(), DataSource.class);
            messageSource = new JdbcPollingChannelAdapter(dataSource, property.getSelectSql());
        }
        messageSource.setMaxRows(property.getMaxRows());
        messageSource.setUpdateSql(property.getUpdateSql());
        messageSource.setUpdatePerRow(property.isUpdatePerRow());
        messageSource.setSelectSqlParameterSource(new MapSqlParameterSource(property.getParams()));
        messageSource.setRowMapper(new BeanPropertyRowMapper<>(ClassUtil.forName(property.getResultClass())));

        messageSource.afterPropertiesSet();
        return messageSource;
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
