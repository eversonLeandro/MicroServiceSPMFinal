package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CommentJpaRepository;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICommentRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.infra.entities.CommentEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentRepositoyAdapter implements ICommentRepositoryPort {
    @Autowired
    private CommentJpaRepository comentJpaRepository;

    @Override
    public Comment AgregateComment(Comment comment) {
        CommentEntity comment1= CommentMapper.DomainToEntity(comment);
        CommentEntity commentEntity= comentJpaRepository.save(comment1);
        return CommentMapper.entityToDomain(commentEntity);
    }

    @Override
    public List<Comment> getAllCommentsByCoordinator(Integer projectId, Integer CoordinatorId) {
        List<CommentEntity> commentEntities=  comentJpaRepository.getCommentEntityByCoordinatorId(projectId);
        return commentEntities.stream().map(CommentMapper::entityToDomain).collect(Collectors.toList());

    }

    @Override
    public List<Comment> GetCommentByProject(Integer nit) {
        List<CommentEntity> commentEntities=  comentJpaRepository.getCommentEntitiesByProjectId(nit);
        return commentEntities.stream().map(CommentMapper::entityToDomain).collect(Collectors.toList());

    }

}
