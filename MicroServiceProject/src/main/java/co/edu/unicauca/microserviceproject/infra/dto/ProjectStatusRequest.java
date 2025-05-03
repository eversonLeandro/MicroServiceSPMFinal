package co.edu.unicauca.microserviceproject.infra.dto;

public class ProjectStatusRequest {
    private Long projectId;
    private String action;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAction() {
        return action;
    }


}
