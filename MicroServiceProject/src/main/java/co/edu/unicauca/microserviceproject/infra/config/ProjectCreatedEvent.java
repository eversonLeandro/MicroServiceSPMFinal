package co.edu.unicauca.microserviceproject.infra.config;

import co.edu.unicauca.microserviceproject.entities.Project;

public class ProjectCreatedEvent {
    private final Project project;

    public ProjectCreatedEvent(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
