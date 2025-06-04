package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;

import java.util.Optional;

public interface ICoordinatorRepositoryPort {
    Optional<Coordinator> findCoordinatorById(Long nit);
    Coordinator saveCoordinator(Coordinator coordinator);
}
