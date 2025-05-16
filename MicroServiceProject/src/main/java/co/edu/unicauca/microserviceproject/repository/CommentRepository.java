package co.edu.unicauca.microserviceproject.repository;


import co.edu.unicauca.microserviceproject.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.projectId = :projectId ORDER BY c.timestamp DESC")
    List<Comment> findByProjectIdOrderByTimestampDesc(@Param("projectId") Integer projectId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.projectId = :projectId")
    Long countByProjectId(@Param("projectId") Integer projectId);

    @Query("SELECT c FROM Comment c WHERE c.projectId = :projectId AND c.coordinatorId = :coordinatorId ORDER BY c.timestamp DESC")
    List<Comment> findByProjectAndCoordinator(
            @Param("projectId") Integer projectId,
            @Param("coordinatorId") Integer coordinatorId);
}