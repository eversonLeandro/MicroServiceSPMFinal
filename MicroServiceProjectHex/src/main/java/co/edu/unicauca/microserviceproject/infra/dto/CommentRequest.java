package co.edu.unicauca.microserviceproject.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {
    private Integer coordinatorId;
    private String coordinatorName;
    private String message;
}
