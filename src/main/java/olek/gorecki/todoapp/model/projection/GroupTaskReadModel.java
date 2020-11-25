package olek.gorecki.todoapp.model.projection;

import olek.gorecki.todoapp.model.Task;

public class GroupTaskReadModel {
    private boolean done;
    private String description;

    public GroupTaskReadModel(Task source) {
        done = source.isDone();
        description = source.getDescription();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
