package olek.gorecki.todoapp.controller;

import olek.gorecki.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;


@RepositoryRestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }


}
