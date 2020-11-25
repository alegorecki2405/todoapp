package olek.gorecki.todoapp.logic;

import olek.gorecki.todoapp.TaskConfigurationProperties;
import olek.gorecki.todoapp.model.ProjectRepository;
import olek.gorecki.todoapp.model.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and the other undone group exist ")
    void createGroup_noMultipleGroupsConfig_And_openGroupExist_IllegalStateException() {
        //given
        TaskGroupRepository mockGroupRepository = groupRepostioryReturning(true);
        TaskConfigurationProperties mockConfig = configurationReturning(false);
        // system under test
        var toTest = new ProjectService(null, mockGroupRepository, mockConfig);
        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));
        //then
        assertThat(exception).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Only one undone group from project is allowed");
    }

    @Test
    @DisplayName("should throw IllegalStateArgumentException when configuration ok and no projects for given id")
    void createGroup_configurationOk_And_noProjects_IllegalArgumentException() {
        //given
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        TaskConfigurationProperties mockConfig = configurationReturning(true);
        // system under test
        var toTest = new ProjectService(mockRepository, null, mockConfig);
        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Project with given id not found");
    }

    @Test
    @DisplayName("should throw IllegalStateArgumentException when configured to allow just 1 group and no groups and no projects")
    void createGroup_noMultipleGroupsConfig_And_noUndoneGroupExists_noProjects_IllegalArgumentException() {
        //given
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        TaskGroupRepository mockGroupRepository = groupRepostioryReturning(false);
        TaskConfigurationProperties mockConfig = configurationReturning(true);
        // system under test
        var toTest = new ProjectService(mockRepository, mockGroupRepository, mockConfig);
        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Project with given id not found");
    }

    @Test
    @DisplayName("should create a new group from project")
    void createGroup_configurationOk_existingProject_createsAndSavesGroup() {
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        TaskGroupRepository mockGroupRepository = groupRepostioryReturning(true);
    }

    private TaskGroupRepository groupRepostioryReturning(final boolean result) {
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByIdIsFalseAndProject_Id(anyInt())).thenReturn(result);
        return mockGroupRepository;
    }

    private TaskConfigurationProperties configurationReturning(boolean result) {
        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTasks()).thenReturn(result);
        var mockConfig = mock(TaskConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        return mockConfig;
    }
}