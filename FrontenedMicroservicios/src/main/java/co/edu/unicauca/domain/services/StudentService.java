 package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.domain.entities.Student;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.IProjectObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brayan
 */
public class StudentService {

    private IRepository repository;
    private final List<IProjectObserver> observadores = new ArrayList<>();

    public StudentService(IRepository repository) {
        this.repository = repository;
    }

    public void agregarObservador(IProjectObserver observador) {
        observadores.add(observador);
    }

    public List<Project> obtenerProyectos() {
        return (List<Project>) (Project) repository.list();
    }

    public boolean registreStudent(Student student) {
        if(validarcampos(student.getNombre(), student.getEmail(), student.getTelefono(),student.getCedula(),student.getCodigo()  )){
        return repository.save(student);
    }else {
            return false;
        }
    }

    public Student obtenerEstudiante(String username) {
        return (Student) repository.found(username);
    }

    private boolean validarcampos(String nombre, String email, String telefono,String cedula,String codigo) {
       if (nombre.matches(".*\\d.*")) {
        Messages.showMessageDialog("El campo Nombre no puede contener números.", "Atención");
        return false;
    }

    // Validar que el email tenga un formato válido
    if (!validarEmail(email)) {
        Messages.showMessageDialog("El campo Email no tiene un formato válido.", "Atención");
        return false;
    }

    // Validar que el teléfono solo contenga números
    if (!telefono.matches("\\d+")) {
        Messages.showMessageDialog("El campo Teléfono solo puede contener números.", "Atención");
        return false;
    }

    // Validar que la cédula solo contenga números
    if (!cedula.matches("\\d+")) {
        Messages.showMessageDialog("El campo Cédula solo puede contener números.", "Atención");
        return false;
    }

    // Validar que el código solo contenga números
    if (!codigo.matches("\\d+")) {
        Messages.showMessageDialog("El campo Código solo puede contener números.", "Atención");
        return false;
    }

    return true; // Todas las validaciones pasaron
}

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}