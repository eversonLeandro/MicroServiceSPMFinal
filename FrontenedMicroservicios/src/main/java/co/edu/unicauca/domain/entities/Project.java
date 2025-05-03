package co.edu.unicauca.domain.entities;

import co.edu.unicauca.infra.ProjectState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author Brayan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private long id;
    private String nitEmpresa;
    private String nombre;
    private String resumen;
    private String descripcion;
    private String objetivo;
    private String tiempoMaximo;
    private String presupuesto;
    private String FechaEntregadaEsperada;
    
    private ProjectState estado;
    private String nombreEmpresa;
    private Company company;
    private Coordination coordinator;
    private List<Postulation> postulations;
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Coordination getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordination coordinator) {
        this.coordinator = coordinator;
    }
    public Project(){
        
    }

    public Project(long id, String nitEmpresa, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada, String estado, String nombreEmpresa) {
        this.id = id;
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.tiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
        this.nombreEmpresa = nombreEmpresa;
    }
   
    public Project(String nitEmpresa, String nombre, String resumen, String descripcion, String objetivo, String TiempoMaximo, String presupuesto, String FechaEntregadaEsperada) {
        this.id = id;
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.tiempoMaximo = TiempoMaximo;
        this.presupuesto = presupuesto;
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
        this.estado = estado;
        this.nombreEmpresa = nombreEmpresa;
      
    }
    
    public long getId() {
        return id;
    }

    // public void avanzarEstado() {
    // this.estado.avanzarEstado(this);
    //}
    //public void NoAvanzaEstado() {
    //  this.estado.NoAvanzaEstado(this);
    //}
    
    public void setId(long id) {    
        this.id = id;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
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
        return tiempoMaximo;
    }

    public void setTiempoMaximo(String TiempoMaximo) {
        this.tiempoMaximo = TiempoMaximo;
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

    public void setFechaEntregadaEsperada(String FechaEntregadaEsperada) {
        this.FechaEntregadaEsperada = FechaEntregadaEsperada;
    }

    public ProjectState getEstado() {
        return estado;
    }

    public String getEstadoString() {
        return estado.getEstado().toString();
    }

    public void setEstado(ProjectState estado) {
        this.estado = estado;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the postulations
     */
    public List<Postulation> getPostulations() {
        return postulations;
    }

    /**
     * @param postulations the postulations to set
     */
    public void setPostulations(List<Postulation> postulations) {
        this.postulations = postulations;
    }

    
}
