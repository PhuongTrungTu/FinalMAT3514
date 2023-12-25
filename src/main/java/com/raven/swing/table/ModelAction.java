package com.raven.swing.table;

import com.raven.Components.Project;

public class ModelAction {

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(Project project, EventAction event) {
        this.project = project;
        this.event = event;
    }

    public ModelAction() {
    }

    private Project project;
    private EventAction event;
}