package co.edu.unicauca.microserviceproject.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long projectId ;


    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "coordinator_id")
    private Long coordinatorId;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;
    private String presupuesto;
    private String fechaEntregadaEsperada;
    private String periodoAcademico;

    private String estado;

    public ProjectEntity() {
    }

    public ProjectEntity(Long companyId, Long coordinatorId, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada, String estado, String periodoAcademico) {
        this.companyId = companyId;
        this.coordinatorId = coordinatorId;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.tiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.fechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.periodoAcademico = periodoAcademico;
    }
    public ProjectEntity(Long projectId,Long companyId, Long coordinatorId, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada, String estado, String periodoAcademico) {
        this.projectId = projectId;
        this.companyId = companyId;
        this.coordinatorId = coordinatorId;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.tiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.fechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.periodoAcademico = periodoAcademico;
    }

    // Relación opcional (solo si necesitas acceder luego)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id", insertable = false, updatable = false)
    private CoordinatorEntity coordinator;

}
