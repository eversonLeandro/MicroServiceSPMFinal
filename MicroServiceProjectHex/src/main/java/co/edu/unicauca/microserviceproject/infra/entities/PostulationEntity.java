package co.edu.unicauca.microserviceproject.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Entity
@AllArgsConstructor
@Getter
@Table(name = "Postulation")
public class PostulationEntity {
    @Id
    private Long id;
    private Long codStudent;
    private Long codProject;
    private String fecha;
    public PostulationEntity(){}

}
