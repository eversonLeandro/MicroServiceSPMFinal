package co.edu.unicauca.access;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.interfaces.IStudentRepository;
import co.edu.unicauca.domain.entities.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class StudentRepository implements IStudentRepository {

    private final String API_URL = "http://localhost:8083/api/Students";

    public StudentRepository() {

    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Object usuario) {
        Student estudiante = (Student) usuario;

        try {
            URL url = new URL(API_URL + "/crear"); // API_URL = http://localhost:8083/api/Students
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Crear el JSON manualmente
            String jsonInputString = String.format("""
        {
            "codEst": "%s",
            "cedula": "%s",
            "telefono": "%s",
            "nombre": "%s",
            "email": "%s"
        }
        """,
                    estudiante.getCodigo(), // este es codEst
                    estudiante.getCedula(),
                    estudiante.getTelefono(),
                    estudiante.getNombre(),
                    estudiante.getEmail());

            // Enviar el JSON al servidor
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener el cÃ³digo de respuesta del servidor
            int code = con.getResponseCode();
            return code == 200 || code == 201;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> list() {
            try {
            URL url = new URL("http://localhost:8083/api/Students/obtnerestudiantes");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<Student> estudiantes = mapper.readValue(inputStream, new TypeReference<List<Student>>() {
            });
            return new ArrayList<>(estudiantes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student found(Object username) {

         try {
        if (username == null) {
            throw new IllegalArgumentException("El parámetro 'username' no puede ser null.");
        }
        String nombre = (String) username;
        URL url = new URL(API_URL + "/obtenerPorUsername/" + URLEncoder.encode(nombre, "UTF-8")); // ✅ CORREGIDO

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Student estudiante = mapper.readValue(inputStream, Student.class);
            System.out.println("Nombre del estudiante: " + estudiante.getNombre());
            return estudiante;
        } else {
            System.err.println("Respuesta HTTP: " + responseCode);
            return null;
        }

    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }

}
