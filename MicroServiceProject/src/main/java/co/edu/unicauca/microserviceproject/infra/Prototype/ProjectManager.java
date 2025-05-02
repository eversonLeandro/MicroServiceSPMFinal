package co.edu.unicauca.microserviceproject.infra.Prototype;

import java.util.HashMap;
import java.util.Map;


public class ProjectManager {
    private Map<String, PrototypeProject> prototipos = new HashMap<>();

    public void registrar(String clave, PrototypeProject prototipo) {
        prototipos.put(clave, prototipo);
    }

    public PrototypeProject clonar(String clave) {
        PrototypeProject prototipo = prototipos.get(clave);
        return prototipo != null ? prototipo.clonar() : null;
    }
}
