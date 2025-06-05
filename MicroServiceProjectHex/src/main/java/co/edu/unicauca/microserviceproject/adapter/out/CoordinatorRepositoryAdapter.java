package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CoordinatorJpaRepository;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICoordinatorRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;
import co.edu.unicauca.microserviceproject.infra.entities.CoordinatorEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CoordinatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CoordinatorRepositoryAdapter implements ICoordinatorRepositoryPort {

    @Autowired
    private CoordinatorJpaRepository coordinatorJpaRepository;

    @Override
    public Optional<Coordinator> findCoordinatorById(Long codCor) {
        CoordinatorEntity coordinatorEntity = coordinatorJpaRepository.getByCodCor(codCor);

        return Optional.ofNullable(coordinatorEntity)
                .map(CoordinatorMapper :: entityToDomain);
    }
}
