package com.alatka.connection.config.model;


import com.alatka.connection.config.property.TaskExecutorProperty;

import java.util.List;

public class TaskExecutorsModel implements DefinitionModel {

    private List<TaskExecutorProperty> taskExecutors;

    public List<TaskExecutorProperty> getTaskExecutors() {
        return taskExecutors;
    }

    public void setTaskExecutors(List<TaskExecutorProperty> taskExecutors) {
        this.taskExecutors = taskExecutors;
    }
}
