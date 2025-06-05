package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.infra.dto.CommentDto;
import co.edu.unicauca.microserviceproject.infra.dto.CommentRequest;
import co.edu.unicauca.microserviceproject.infra.entities.CommentEntity;

import java.time.LocalDateTime;

public class CommentMapper {
    public static Comment entityToDomain(CommentEntity entity) {
        if (entity == null) return null;
        return new Comment(
                entity.getId(),
                entity.getProjectId(),
                entity.getCoordinatorId(),
                entity.getCoordinatorName(),
                entity.getMessage(),
                LocalDateTime.parse(entity.getFecha())
        );
    }

    public static CommentEntity DomainToEntity(Comment comment) {
        if (comment == null) return null;
        return new CommentEntity(
        comment.getId(),
        comment.getProjectId(),
        comment.getCoordinatorId(),
        comment.getCoordinatorName(),
        comment.getMessage(),
        String.valueOf(comment.getTimestamp())
        );
    }

    public static Comment crear(Integer projectId, CommentRequest request) {
        return  new Comment(
                null,
                projectId,
                request.getCoordinatorId(),
                request.getCoordinatorName(),
                request.getMessage(),
                LocalDateTime.now()
        );
    }

    public static CommentRequest domainToDto(Comment comment) {
        if (comment == null) return null;
        return new CommentRequest(
                comment.getCoordinatorId(),
                comment.getCoordinatorName(),
                comment.getMessage()
        );
    }

}
