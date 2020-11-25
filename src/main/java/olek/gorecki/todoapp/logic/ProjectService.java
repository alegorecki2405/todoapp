package olek.gorecki.todoapp.logic;

import olek.gorecki.todoapp.TaskConfigurationProperties;
import olek.gorecki.todoapp.model.*;
import olek.gorecki.todoapp.model.projection.GroupReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties config;


    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository,
                          TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
    }

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(final Project toSave) {
        return repository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {

        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByIdIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(project.getSteps().stream()
                            .map(projectStep -> new Task(
                                    projectStep.getDescription(),
                                    deadline.plusDays(projectStep.getDaysToDeadline()))
                            ).collect(Collectors.toSet())
                    );
                    targetGroup.setProject(project);
                    return taskGroupRepository.save(targetGroup);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }
}
