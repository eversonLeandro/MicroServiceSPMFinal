package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Project;
import java.util.List;

public interface IProjectObserver {
    void actualizarProyectos(List<Project> proyectos);
    
}
