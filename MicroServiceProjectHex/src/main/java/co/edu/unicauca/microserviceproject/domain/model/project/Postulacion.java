package co.edu.unicauca.microserviceproject.domain.model.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Postulacion {
    private Long id;
    private Long codEstudiante;
    private Long projectId;
    private LocalDateTime fecha;

}
