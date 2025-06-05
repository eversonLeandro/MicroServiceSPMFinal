package co.edu.unicauca.microserviceproject.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "Comment")
@Entity
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "project_id", length = 255)
    private Integer projectId;

    @Column(name = "coordinator_id", length = 255)
    private Integer coordinatorId;

    @Column(name = "coordinator_name", length = 255)
    private String coordinatorName;

    @Column(length = 1000)
    private String message;

    @Column(length = 50)
    private String fecha;

    public CommentEntity() {
    }
}
