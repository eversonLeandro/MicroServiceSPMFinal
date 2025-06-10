package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.project.Comment;
import co.edu.unicauca.microserviceproject.infra.dto.CommentRequest;
import co.edu.unicauca.microserviceproject.infra.entities.CommentEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {

    @Test
    void testEntityToDomain() {
        CommentEntity entity = new CommentEntity(
                1,
                100,
                200,
                "Coord Name",
                "Test message",
                "2025-01-01T10:00:00"
        );

        Comment comment = CommentMapper.entityToDomain(entity);

        assertNotNull(comment);
        assertEquals(1, comment.getId());
        assertEquals(100, comment.getProjectId());
        assertEquals("Test message", comment.getMessage());
    }

    @Test
    void testDomainToEntity() {
        Comment comment = new Comment(
                1,
                100,
                200,
                "Coord Name",
                "Test message",
                LocalDateTime.of(2025, 1, 1, 10, 0)
        );

        CommentEntity entity = CommentMapper.DomainToEntity(comment);

        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals(100, entity.getProjectId());
        assertEquals("Test message", entity.getMessage());
    }

    @Test
    void testCrearFromRequest() {
        CommentRequest request = new CommentRequest(
                200,
                "Coord Name",
                "Test message"
        );

        Comment comment = CommentMapper.crear(100, request);

        assertNotNull(comment);
        assertEquals(100, comment.getProjectId());
        assertEquals(200, comment.getCoordinatorId());
        assertEquals("Test message", comment.getMessage());
    }

    @Test
    void testDomainToDto() {
        Comment comment = new Comment(
                1,
                100,
                200,
                "Coord Name",
                "Test message",
                LocalDateTime.of(2025, 1, 1, 10, 0)
        );

        CommentRequest dto = CommentMapper.domainToDto(comment);

        assertNotNull(dto);
        assertEquals(200, dto.getCoordinatorId());
        assertEquals("Test message", dto.getMessage());
    }
}