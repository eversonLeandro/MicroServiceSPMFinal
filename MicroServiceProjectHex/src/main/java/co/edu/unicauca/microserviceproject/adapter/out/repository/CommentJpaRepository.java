package co.edu.unicauca.microserviceproject.adapter.out.repository;
import co.edu.unicauca.microserviceproject.infra.entities.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {


    List<CommentEntity> getCommentEntitiesByProjectId(Integer nit);

    List<CommentEntity> getCommentEntityByCoordinatorId(Integer projectId);
}
