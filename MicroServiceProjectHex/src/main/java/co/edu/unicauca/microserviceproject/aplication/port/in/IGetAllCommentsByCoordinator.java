package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Comment;

import java.util.List;

public interface IGetAllCommentsByCoordinator {
    List<Comment> getAllComments(Integer projectId,Integer coodinatorId);
}
