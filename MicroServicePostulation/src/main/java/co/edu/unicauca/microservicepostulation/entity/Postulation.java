package co.edu.unicauca.microservicepostulation.entity;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


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

    @NotNull
    private Long idEstudiante;
    @NotNull
    private Long idProyecto;
    @NotNull
    private LocalDateTime fechaPostulacion;
}
