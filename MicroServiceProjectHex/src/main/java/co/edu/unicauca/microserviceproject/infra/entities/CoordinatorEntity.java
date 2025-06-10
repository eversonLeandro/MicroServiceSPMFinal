package co.edu.unicauca.microserviceproject.infra.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Table(name = "Coordinator")
@Entity
@Getter
@AllArgsConstructor
@Setter
public class CoordinatorEntity {
    @Id
    private Long codCor;
    private String estado;
    private String gmail;

    public CoordinatorEntity() {}

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = "ACTIVO";
        }
    }
}
