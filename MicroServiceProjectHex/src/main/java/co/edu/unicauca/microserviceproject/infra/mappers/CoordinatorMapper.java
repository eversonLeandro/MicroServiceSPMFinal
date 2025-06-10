package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.VO.Email;
import co.edu.unicauca.microserviceproject.infra.entities.CoordinatorEntity;

public class CoordinatorMapper {
    public static Coordinator entityToDomain(CoordinatorEntity entity) {
        if (entity == null) return null;
        return new Coordinator(entity.getCodCor(),
                entity.getEstado(),
                new Email(entity.getGmail())
        );
    }
    public static CoordinatorEntity domainToEntity(Coordinator coordinator) {
        if (coordinator == null) return null;
        return new CoordinatorEntity(
                coordinator.getCodCor(),
                coordinator.getEstado(),
                coordinator.getEmail().getEmail()
        );
    }
}
