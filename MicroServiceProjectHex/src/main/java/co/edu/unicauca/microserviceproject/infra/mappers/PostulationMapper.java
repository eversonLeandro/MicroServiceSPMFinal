package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Postulacion;
import co.edu.unicauca.microserviceproject.infra.dto.PostulationDTO;
import co.edu.unicauca.microserviceproject.infra.entities.PostulationEntity;

import java.time.LocalDateTime;

public class PostulationMapper {
    public static PostulationEntity domainToEntity(Postulacion entity) {
        if (entity == null) return null;
        return new PostulationEntity(
                entity.getId(),
                entity.getCodEstudiante(),
                entity.getProjectId(),
                String.valueOf(entity.getFecha())
        );
    }

    public static Postulacion entityToDomain(PostulationEntity postulationEntity) {
        if (postulationEntity == null) return null;

        return new Postulacion(
                postulationEntity.getId(),
                postulationEntity.getCodStudent(),
                postulationEntity.getCodProject(),
                LocalDateTime.parse(postulationEntity.getFecha())
        );
    }

    public static PostulationEntity dtoToEntity(PostulationDTO entity) {
        if (entity == null) return null;
        return new PostulationEntity(
                entity.getIdPostulation(),
                Long.parseLong(entity.getCodStudent()),
                entity.getCodProject(),
                String.valueOf(entity.getFecha())
        );
    }
}
