package com.alatka.connection.config.model;


import com.alatka.connection.config.property.TaskSchedulerProperty;

import java.util.List;

public class TaskSchedulersModel implements DefinitionModel {

    private List<TaskSchedulerProperty> taskSchedulers;

    public List<TaskSchedulerProperty> getTaskSchedulers() {
        return taskSchedulers;
    }

    public void setTaskSchedulers(List<TaskSchedulerProperty> taskSchedulers) {
        this.taskSchedulers = taskSchedulers;
    }
}
