package co.edu.unicauca.microserviceproject.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Setter
@Table(name = "Company")
@Getter
public class CompanyEntity {

    @Id
    @NotNull
    private Long nit;
    private String nombre;
    private String email;
    private String estado;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProjectEntity> projects = new ArrayList<>();

    public CompanyEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = "ACTIVO";
        }
    }
}

