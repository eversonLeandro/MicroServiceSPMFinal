package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CommentJpaRepository;
import co.edu.unicauca.microserviceproject.adapter.out.repository.ProjectJpaRepository;
import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.infra.entities.CommentEntity;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CommentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentRepositoyAdapterTest {

    @Mock
    private CommentJpaRepository commentJpaRepository;

    @Mock
    private ProjectJpaRepository projectJpaRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentRepositoyAdapter commentRepositoryAdapter;

    @Test
    void testAgregateComment() {
        Comment comment = new Comment(1, 100, 200, "Coord", "Message", LocalDateTime.now());
        CommentEntity entity = new CommentEntity(1, 100, 200, "Coord", "Message", LocalDateTime.now().toString());

        try (MockedStatic<CommentMapper> mocked = Mockito.mockStatic(CommentMapper.class)) {
            mocked.when(() -> CommentMapper.DomainToEntity(comment)).thenReturn(entity);
            mocked.when(() -> CommentMapper.entityToDomain(entity)).thenReturn(comment);

            when(commentJpaRepository.save(entity)).thenReturn(entity);

            Comment savedComment = commentRepositoryAdapter.AgregateComment(comment);

            assertNotNull(savedComment);
            verify(commentJpaRepository).save(entity);
        }
    }


    @Test
    void testGetAllCommentsByCoordinator() {
        ProjectEntity mockEntity = new ProjectEntity();
        mockEntity.setProjectId(100L);
        CommentEntity entity = new CommentEntity(1, 100, 200, "Coord", "Message", LocalDateTime.now().toString());
        Comment comment = new Comment(1, 100, 200, "Coord", "Message", LocalDateTime.now());

        when(commentJpaRepository.getCommentEntityByCoordinatorId(100)).thenReturn(List.of(entity));

        try (MockedStatic<CommentMapper> mocked = Mockito.mockStatic(CommentMapper.class)) {
            mocked.when(() -> CommentMapper.entityToDomain(entity)).thenReturn(comment);

            List<Comment> result = commentRepositoryAdapter.getAllCommentsByCoordinator(100, 200);

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
        }
    }

}