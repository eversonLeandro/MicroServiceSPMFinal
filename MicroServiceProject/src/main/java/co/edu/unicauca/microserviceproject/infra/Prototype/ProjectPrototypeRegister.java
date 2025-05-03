package co.edu.unicauca.microserviceproject.infra.Prototype;

import co.edu.unicauca.microserviceproject.entities.Project;
import co.edu.unicauca.microserviceproject.infra.states.RecibidoState;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProjectPrototypeRegister {

    private final ProjectManager gestorPrototipos = new ProjectManager();

    public ProjectManager getGestor() {
        return gestorPrototipos;
    }

    @PostConstruct
    public void inicializar() {
        Project plantilla = new Project();

        plantilla.setNombre("Nombre del proyecto");
        plantilla.setResumen("Resumen breve del proyecto.");
        plantilla.setDescripcion("Descripción general del proyecto.");
        plantilla.setObjetivo("Objetivo principal del proyecto.");
        plantilla.setTiempoMaximo("Por definir");
        plantilla.setPresupuesto("Por definir");
        plantilla.setFechaEntregadaEsperada("Por definir");
        plantilla.setPostulations(new ArrayList<>());
        plantilla.setCompany(null);
        plantilla.setCoordinator(null);
        plantilla.setEstado(new RecibidoState());
        plantilla.setEstadoTexto("RECIBIDO");


        gestorPrototipos.registrar("DEFECTO", plantilla);
    }
}
