package co.edu.unicauca.microserviceproject.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "Comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer projectId;

    @Column(nullable = false)
    private Integer coordinatorId;

    @Column(nullable = false, length = 100)
    private String coordinatorName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CommentEntity() {}
}
