package com.alatka.connection.core.model;


import com.alatka.connection.core.property.support.TaskSchedulerProperty;

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
