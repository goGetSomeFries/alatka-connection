package com.alatka.connection.core.model;


import com.alatka.connection.core.property.support.TaskExecutorProperty;

import java.util.List;

public class TaskExecutorsModel implements DefinitionModel<TaskExecutorProperty> {

    private List<TaskExecutorProperty> taskExecutors;

    public List<TaskExecutorProperty> getTaskExecutors() {
        return taskExecutors;
    }

    public void setTaskExecutors(List<TaskExecutorProperty> taskExecutors) {
        this.taskExecutors = taskExecutors;
    }

    @Override
    public Class<TaskExecutorProperty> propertyClass() {
        return TaskExecutorProperty.class;
    }

    @Override
    public List<TaskExecutorProperty> obtainProperties() {
        return taskExecutors;
    }
}
