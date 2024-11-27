package com.alatka.connection.jdbc.support;

import org.springframework.integration.handler.MessageProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.messaging.Message;

import javax.sql.DataSource;
import java.util.Map;

public class JdbcMessageProcessor implements MessageProcessor<Object> {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private boolean multiple;

    private String sql;

    private Class<?> resultClass;

    public JdbcMessageProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public JdbcMessageProcessor(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Object processMessage(Message<?> message) {
        Object payload = message.getPayload();
        SqlParameterSource sqlParameterSource = payload instanceof Map ?
                new MapSqlParameterSource((Map<String, ?>) payload) : new BeanPropertySqlParameterSource(payload);
        if (multiple) {
            return resultClass == null ? jdbcTemplate.queryForList(sql, sqlParameterSource) :
                    this.jdbcTemplate.queryForList(sql, sqlParameterSource, resultClass);
        }
        return resultClass == null ? jdbcTemplate.queryForMap(sql, sqlParameterSource) :
                this.jdbcTemplate.queryForObject(sql, sqlParameterSource, resultClass);
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setResultClass(Class<?> resultClass) {
        this.resultClass = resultClass;
    }
}