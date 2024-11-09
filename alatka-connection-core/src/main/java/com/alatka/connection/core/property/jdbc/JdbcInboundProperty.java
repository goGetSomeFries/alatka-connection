package com.alatka.connection.core.property.jdbc;

import com.alatka.connection.core.property.core.SourcePollingInboundProperty;

import javax.validation.constraints.NotBlank;
import java.util.Map;

public class JdbcInboundProperty extends SourcePollingInboundProperty {

    private String dataSource;

    private String jdbcTemplate;

    @NotBlank
    private String selectSql;

    private String updateSql;

    private int maxRows;

    private boolean updatePerRow;

    private Map<String, Object> params;

    @NotBlank
    private String resultClass;

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

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public boolean isUpdatePerRow() {
        return updatePerRow;
    }

    public void setUpdatePerRow(boolean updatePerRow) {
        this.updatePerRow = updatePerRow;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }
}
