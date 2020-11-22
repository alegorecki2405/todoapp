package olek.gorecki.todoapp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById();

    TaskGroup save(TaskGroup entity);
}
