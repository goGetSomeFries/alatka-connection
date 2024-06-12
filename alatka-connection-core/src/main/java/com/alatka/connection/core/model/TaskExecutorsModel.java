package com.alatka.connection.core.model;

import com.alatka.connection.core.property.TaskExecutorProperty;

import java.util.List;

public class TaskExecutorsModel extends DefinitionModel {

    private List<TaskExecutorProperty> taskExecutors;

    public List<TaskExecutorProperty> getTaskExecutors() {
        return taskExecutors;
    }

    public void setTaskExecutors(List<TaskExecutorProperty> taskExecutors) {
        this.taskExecutors = taskExecutors;
    }
}
