package com.alatka.connection.core.property.jdbc;

import com.alatka.connection.core.property.core.OutboundProperty;

import javax.validation.constraints.NotBlank;

public class JdbcOutboundProperty extends OutboundProperty {

    private String dataSource;

    private String jdbcTemplate;

    @NotBlank
    private String sql;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(String jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
