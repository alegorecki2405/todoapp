package olek.gorecki.todoapp.logic;

import olek.gorecki.todoapp.TaskConfigurationProperties;
import olek.gorecki.todoapp.model.ProjectRepository;
import olek.gorecki.todoapp.model.TaskGroupRepository;
import olek.gorecki.todoapp.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LogicConfiguration {
    @Bean
    ProjectService projectService(final ProjectRepository repository,
                                  final TaskGroupRepository taskGroupRepository,
                                  final TaskGroupService taskGroupService,
                                  final TaskConfigurationProperties config) {
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    TaskGroupService taskGroupService(final TaskGroupRepository repository,
                                      final TaskRepository taskRepository) {
        return new TaskGroupService(repository, taskRepository);
    }
}
