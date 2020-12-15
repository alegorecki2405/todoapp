package olek.gorecki.todoapp.model.event;

import olek.gorecki.todoapp.model.Task;

import java.time.Clock;

public class TaskDone extends TaskEvent {
    public TaskDone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
