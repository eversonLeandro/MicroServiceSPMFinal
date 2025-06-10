package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetCommentByProject;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICommentRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetCommentByProjectUseCase implements IGetCommentByProject {
    private final ICommentRepositoryPort commentRepositoryPort;
    @Override
   public List<Comment> GetCommentByProject(Integer nit){
        return commentRepositoryPort.GetCommentByProject(nit);
    }

}
