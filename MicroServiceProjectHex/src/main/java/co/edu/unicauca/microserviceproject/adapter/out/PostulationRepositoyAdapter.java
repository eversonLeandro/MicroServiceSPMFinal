package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.postulationJpaRepository;
import co.edu.unicauca.microserviceproject.aplication.port.out.IPostulationRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Postulacion;
import co.edu.unicauca.microserviceproject.infra.dto.PostulationDTO;
import co.edu.unicauca.microserviceproject.infra.entities.PostulationEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.PostulationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostulationRepositoyAdapter implements IPostulationRepositoryPort {
    @Autowired
    private postulationJpaRepository postulationJpaRepository;
    @Override
    public Postulacion savePostulation(PostulationDTO postulation) {
        PostulationEntity entity = PostulationMapper.dtoToEntity(postulation);
        PostulationEntity postulationEntity= postulationJpaRepository.save(entity);
        return PostulationMapper.entityToDomain(postulationEntity);
    }
}
