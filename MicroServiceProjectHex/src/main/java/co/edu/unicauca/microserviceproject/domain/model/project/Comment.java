package co.edu.unicauca.microserviceproject.domain.model.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Comment {
    private Integer id;
    private  Integer projectId;
    private  Integer coordinatorId;
    private  String coordinatorName;
    private  String message;
    private  LocalDateTime timestamp;

}
