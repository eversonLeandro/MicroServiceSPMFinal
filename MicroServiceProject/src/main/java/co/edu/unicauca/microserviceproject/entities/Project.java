package co.edu.unicauca.microserviceproject.entities;


import co.edu.unicauca.microserviceproject.infra.Prototype.PrototypeProject;
import co.edu.unicauca.microserviceproject.infra.states.EstadoFactory;
import co.edu.unicauca.microserviceproject.infra.states.ProjectState;
import co.edu.unicauca.microserviceproject.infra.states.RecibidoState;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.List;

@Entity
public class Project implements PrototypeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "estado")
    private String estadoTexto;

    @Transient
    private ProjectState estado;

    @ManyToOne
    @JoinColumn(name = "nit", referencedColumnName = "nit")
    @NotNull
    private Company company;

    @ManyToOne
    @JoinColumn(name="codCor",referencedColumnName = "codCor")
    private Coordinator coordinator;

    @OneToMany
    @JoinColumn(name = "codProject")
    private List<Postulation> postulations;

    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String TiempoMaximo;
    private String presupuesto;
    private String FechaEntregadaEsperada;



    public Project() {
        this.estadoTexto = "RECIBIDO";
        this.estado = new RecibidoState();
    }

    public Project(Company Company, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada, ProjectState estado) {
        this.company = Company;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.TiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.estado = new RecibidoState(); // Estado inicial
    }
    public Project(ProjectState estado) {
        this.estado = new RecibidoState();
    }
    @PrePersist
    public void estado() {
        if(getEstado() == null){
            setEstado(new RecibidoState());
        }
    }
    @PostLoad
    public void inicializarEstado() {
        this.estado = EstadoFactory.crearEstado(estadoTexto);
    }
    public void nextState() {
        this.estado.avanzarEstado(this);
    }

    public void NoNextState() {
        this.estado.NoAvanzaEstado(this);
    }

    public ProjectState getEstado() {
        return estado;
    }

    public void setEstado(ProjectState estado) {
        this.estado = estado;
        this.estadoTexto = estado.getEstado();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTiempoMaximo() {
        return TiempoMaximo;
    }

    public void setTiempoMaximo(String tiempoMaximo) {
        TiempoMaximo = tiempoMaximo;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getFechaEntregadaEsperada() {
        return FechaEntregadaEsperada;
    }

    public void setFechaEntregadaEsperada(String fechaEntregadaEsperada) {
        FechaEntregadaEsperada = fechaEntregadaEsperada;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public List<Postulation> getPostulations() {
        return postulations;
    }

    public void setPostulations(List<Postulation> postulations) {
        this.postulations = postulations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoTexto() {
        return estadoTexto;
    }

    public void setEstadoTexto(String estadoTexto) {
        this.estadoTexto = estadoTexto;
    }

    @Override
    public PrototypeProject clonar() {
        return new Project(company,nombre,resumen,descripcion,objetivo,TiempoMaximo,presupuesto,FechaEntregadaEsperada,estado);
    }
    public Project clone() {
        Project clone = new Project(company,nombre,resumen,descripcion,objetivo,TiempoMaximo,presupuesto,FechaEntregadaEsperada,estado);
        clone.setEstado(new RecibidoState());
        clone.setEstadoTexto("RECIBIDO");
        return clone;
}
}


