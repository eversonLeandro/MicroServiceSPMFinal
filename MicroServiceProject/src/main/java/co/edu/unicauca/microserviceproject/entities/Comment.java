package co.edu.unicauca.microserviceproject.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer projectId;

    @Column(nullable = false)
    private Integer coordinatorId;

    @Column(nullable = false, length = 100)
    private String coordinatorName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Comment() {}

    public Comment(Integer projectId, Integer coordinatorId, String coordinatorName, String message) {
        this.projectId = projectId;
        this.coordinatorId = coordinatorId;
        this.coordinatorName = coordinatorName;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public Integer getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(Integer coordinatorId) { this.coordinatorId = coordinatorId; }

    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}