package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private Integer coordinatorId;
    private String coordinatorName;
    private String message;
    private String timestamp;

    public CommentDto(String coordinatorName, String message, LocalDateTime timestamp) {
        this.coordinatorName = coordinatorName;
        this.message = message;
        this.timestamp = timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
