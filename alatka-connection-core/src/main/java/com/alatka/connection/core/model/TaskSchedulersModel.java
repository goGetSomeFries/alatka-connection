package com.alatka.connection.core.model;


import com.alatka.connection.core.property.support.TaskSchedulerProperty;

import java.util.List;

public class TaskSchedulersModel implements DefinitionModel<TaskSchedulerProperty> {

    private List<TaskSchedulerProperty> taskSchedulers;

    public List<TaskSchedulerProperty> getTaskSchedulers() {
        return taskSchedulers;
    }

    public void setTaskSchedulers(List<TaskSchedulerProperty> taskSchedulers) {
        this.taskSchedulers = taskSchedulers;
    }

    @Override
    public Class<TaskSchedulerProperty> propertyClass() {
        return TaskSchedulerProperty.class;
    }

    @Override
    public List<TaskSchedulerProperty> obtainProperties() {
        return taskSchedulers;
    }
}
