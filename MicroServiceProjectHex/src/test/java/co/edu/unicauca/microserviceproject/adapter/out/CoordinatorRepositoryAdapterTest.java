package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CoordinatorJpaRepository;
import co.edu.unicauca.microserviceproject.domain.model.coordinator.Coordinator;
import co.edu.unicauca.microserviceproject.infra.entities.CoordinatorEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CompanyMapper;
import co.edu.unicauca.microserviceproject.infra.mappers.CoordinatorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoordinatorRepositoryAdapterTest {

    @Mock
    private CoordinatorJpaRepository coordinatorJpaRepository;

    @Mock
    private CoordinatorMapper coordinatorMapper;

    @InjectMocks
    private CoordinatorRepositoryAdapter coordinatorRepositoryAdapter;

    @Test
    void testFindCoordinatorById() {
        CoordinatorEntity entity = new CoordinatorEntity();
        entity.setCodCor(123L);
        Coordinator coordinator = new Coordinator(123L, "ACTIVO", null);

        try (MockedStatic<CoordinatorMapper>mocked = Mockito.mockStatic(CoordinatorMapper.class)){
        when(coordinatorJpaRepository.getByCodCor(123L)).thenReturn(entity);
        mocked.when(() -> coordinatorMapper.entityToDomain(entity)).thenReturn(coordinator);

        Optional<Coordinator> result = coordinatorRepositoryAdapter.findCoordinatorById(123L);

        assertTrue(result.isPresent());
        assertEquals(123L, result.get().getCodCor());
        }
    }
}