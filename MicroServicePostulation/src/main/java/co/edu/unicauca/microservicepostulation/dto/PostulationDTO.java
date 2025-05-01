package co.edu.unicauca.microservicepostulation.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostulationDTO  implements Serializable{
    private Long idPostulation;
    private String codStudent;
    private Long codProject;
    private Timestamp postulationDate;
}
