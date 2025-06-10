package co.edu.unicauca.microserviceproject.aplication.port.out;

import co.edu.unicauca.microserviceproject.domain.model.project.Comment;

import java.util.List;

public interface ICommentRepositoryPort {
    Comment AgregateComment(Comment comment);
    List<Comment> getAllCommentsByCoordinator(Integer projectId, Integer CoordinatorId);
    List<Comment> GetCommentByProject(Integer nit);
}
