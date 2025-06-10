package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.domain.model.project.Postulacion;
import co.edu.unicauca.microserviceproject.infra.dto.PostulationDTO;
import co.edu.unicauca.microserviceproject.infra.entities.PostulationEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CoordinatorMapper;
import co.edu.unicauca.microserviceproject.infra.mappers.PostulationMapper;
import co.edu.unicauca.microserviceproject.adapter.out.repository.postulationJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostulationRepositoyAdapterTest {

    @Mock
    private postulationJpaRepository postulationJpaRepository;

    @Mock
    private PostulationMapper postulationMapper;

    @InjectMocks
    private PostulationRepositoyAdapter postulationRepositoryAdapter;

    @Test
    void testSavePostulation() {
        PostulationDTO dto = new PostulationDTO(1L, "123", 100L, LocalDateTime.now());
        PostulationEntity entity = new PostulationEntity();
        Postulacion postulacion = new Postulacion(1L, 123L, 100L, LocalDateTime.now());

        try (MockedStatic <PostulationMapper> mocked = Mockito.mockStatic(PostulationMapper.class)) {
            mocked.when(() -> postulationMapper.dtoToEntity(dto)).thenReturn(entity);
            when(postulationJpaRepository.save(entity)).thenReturn(entity);
            mocked.when(() -> postulationMapper.entityToDomain(entity)).thenReturn(postulacion);

            Postulacion result = postulationRepositoryAdapter.savePostulation(dto);

            assertNotNull(result);
            assertEquals(1L, result.getId());
        }
    }
}