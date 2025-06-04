package co.edu.unicauca.microserviceproject.infra.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostulationDTO implements Serializable{
    private Long idPostulation;
    private String codStudent;
    private Long codProject;
    private LocalDateTime fecha;
}
