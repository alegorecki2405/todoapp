package olek.gorecki.todoapp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(Integer i);

    TaskGroup save(TaskGroup entity);

    boolean existsByIdIsFalseAndProject_Id(Integer projectId);
}
