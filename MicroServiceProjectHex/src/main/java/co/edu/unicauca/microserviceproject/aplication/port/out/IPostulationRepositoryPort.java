package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.domain.model.project.Postulacion;
import co.edu.unicauca.microserviceproject.infra.dto.PostulationDTO;

public interface IPostulationRepositoryPort {
    Postulacion savePostulation(PostulationDTO postulation);
}
