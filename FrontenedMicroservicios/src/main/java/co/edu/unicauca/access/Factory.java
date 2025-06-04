package co.edu.unicauca.access;

import co.edu.unicauca.interfaces.IRepository;
import java.util.HashMap;
import java.util.Map;

public class Factory {

    private static Factory instance;
    private Map<String, IRepository> dictionary;

    private Factory() {
        dictionary = new HashMap<>();
        dictionary.put("company", new CompanyRepository());
        dictionary.put("student", new StudentRepository());
        dictionary.put("project", new ProjectRepository());
        dictionary.put("postulation", new PostulationRepository());
        dictionary.put("usuario", new UserRepository());
        
    }

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public IRepository getRepository(String repository) {
        IRepository repo = dictionary.get(repository.toLowerCase());
        if (repo == null) {
            throw new IllegalArgumentException("No se encontró el repositorio: " + repository);
        }
        return repo;
    }
}
