package co.edu.unicauca.microservicepostulation.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Postulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long idEstudiante;
    private Long idProyecto;
    private LocalDateTime fechaPostulacion;
}
