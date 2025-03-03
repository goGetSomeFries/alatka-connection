package com.alatka.connection.core.property.jdbc;

import com.alatka.connection.core.property.core.MessageProcessorProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * alatka.connection.flow.processors[n].handler.jdbc
 *
 * @author whocares
 */
public class JdbcHandlerProperty extends MessageProcessorProperty {

    private String jdbcTemplate;

    private String dataSource;

    private String resultClass;

    private String sql;

    private boolean multiple;

    public String getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(String jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @JsonIgnore
    @Override
    public String getExpression() {
        return super.getExpression();
    }

    @JsonIgnore
    @Override
    public String getBeanName() {
        return super.getBeanName();
    }

    @JsonIgnore
    @Override
    public String getClassName() {
        return super.getClassName();
    }
}
