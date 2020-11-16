package olek.gorecki.todoapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private boolean allowMultipleTasksFromTemplate;

    public boolean isAllowMultipleTasksFromTemplate() {
        return allowMultipleTasksFromTemplate;
    }

    public void setAllowMultipleTasksFromTemplate(boolean allowMultipleTasksFromTemplate) {
        this.allowMultipleTasksFromTemplate = allowMultipleTasksFromTemplate;
    }
}
