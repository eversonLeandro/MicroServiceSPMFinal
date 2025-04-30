package co.edu.unicauca.domain.services;


import co.edu.unicauca.infra.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brayan
 */
public class StudentService {

   
    public StudentService() {
       
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
