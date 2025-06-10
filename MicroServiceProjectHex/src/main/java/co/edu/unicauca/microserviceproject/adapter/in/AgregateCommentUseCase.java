package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IAgregateComment;
import co.edu.unicauca.microserviceproject.aplication.port.out.ICommentRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AgregateCommentUseCase implements IAgregateComment {
    private final ICommentRepositoryPort commentRepositoryPort;
    @Override
    public Comment AgregateComment(Comment comment) {
       return commentRepositoryPort.AgregateComment(comment);
    }
}
