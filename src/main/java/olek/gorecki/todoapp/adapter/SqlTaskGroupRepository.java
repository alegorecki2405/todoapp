package olek.gorecki.todoapp.adapter;

import olek.gorecki.todoapp.model.TaskGroup;
import olek.gorecki.todoapp.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskGroupRepository extends TaskGroupRepository,JpaRepository<TaskGroup, Integer> {
}
