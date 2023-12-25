package com.raven.swing.table;

import com.raven.Components.Task;

public class ModelAction1 {

    public EventAction1 getEvent() {
        return event;
    }

    public void setEvent(EventAction1 event) {
        this.event = event;
    }
    
    public ModelAction1(Task task, EventAction1 event) {
        this.task = task;
        this.event = event;
    }

    public ModelAction1() {
    }
    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    private Task task;
    private EventAction1 event;
}
