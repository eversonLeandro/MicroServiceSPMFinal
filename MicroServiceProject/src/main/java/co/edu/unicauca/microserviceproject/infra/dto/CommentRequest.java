package co.edu.unicauca.microserviceproject.infra.dto;

public class CommentRequest {
    private Integer coordinatorId;
    private String coordinatorName;
    private String message;

    public Integer getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(Integer coordinatorId) { this.coordinatorId = coordinatorId; }

    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}