package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllCommentsByCoordinator;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICommentRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetAllCommentUseCaseByCoordinator implements IGetAllCommentsByCoordinator {
    private final ICommentRepositoryPort commentRepositoryPort;
    @Override
    public List<Comment> getAllComments(Integer projectId , Integer coordinatorId){
        return commentRepositoryPort.getAllCommentsByCoordinator(projectId,coordinatorId);
    }
}
