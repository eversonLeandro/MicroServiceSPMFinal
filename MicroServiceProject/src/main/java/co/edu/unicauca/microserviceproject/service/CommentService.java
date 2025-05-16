package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.entities.Comment;
import co.edu.unicauca.microserviceproject.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByProject(Integer projectId) {
        return commentRepository.findByProjectIdOrderByTimestampDesc(projectId);
    }

    @Transactional
    public Comment addComment(Integer projectId, Integer coordinatorId,
                              String coordinatorName, String message) {
        Comment comment = new Comment(projectId, coordinatorId, coordinatorName, message);
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Long countCommentsByProject(Integer projectId) {
        return commentRepository.countByProjectId(projectId);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByProjectAndCoordinator(Integer projectId, Integer coordinatorId) {
        return commentRepository.findByProjectAndCoordinator(projectId, coordinatorId);
    }
}