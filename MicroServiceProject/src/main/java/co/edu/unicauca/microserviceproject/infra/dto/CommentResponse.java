package co.edu.unicauca.microserviceproject.infra.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentResponse {
    private String coordinatorName;
    private String message;
    private String timestamp;

    public CommentResponse(String coordinatorName, String message, LocalDateTime timestamp) {
        this.coordinatorName = coordinatorName;
        this.message = message;
        this.timestamp = timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getCoordinatorName() { return coordinatorName; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
}
