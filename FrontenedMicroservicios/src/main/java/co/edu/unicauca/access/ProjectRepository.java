/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.access;

import co.edu.unicauca.interfaces.IProjectRepository;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.User;
/*import co.edu.unicauca.domain.states.AceptadoState;
import co.edu.unicauca.domain.states.EnEjecucionState;
import co.edu.unicauca.domain.states.RechazadoState;
import co.edu.unicauca.domain.states.RecibidoState;
import co.edu.unicauca.domain.states.CerradoState;
 */
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.infra.ProjectState;
import co.edu.unicauca.infra.dto.ProjectStatusRequest;
import co.edu.unicauca.infra.dto.ProjectRequestCompany;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

//import co.edu.unicauca.interfaces.ProjectState;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class ProjectRepository implements IProjectRepository {

    public ProjectRepository() {

    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
public boolean save(Object entity) {
    Project project = (Project) entity;

    try {
        URL url = new URL("http://localhost:8080/apiProject/project");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        // Crear el JSON manualmente
        String jsonInputString = String.format("""
        {
            "nitCompany": "%s",
            "nombre": "%s",
            "resumen": "%s",
            "descripcion": "%s",
            "objetivo": "%s",
            "tiempoMaximo": "%s",
            "presupuesto": "%s",
            "fechaEntregadaEsperada": "%s"
        }
        """,
            project.getNitEmpresa(),
            project.getNombre(),
            project.getResumen(),
            project.getDescripcion(),
            project.getObjetivo(),
            project.getTiempoMaximo(),
            project.getPresupuesto(),
            project.getFechaEntregadaEsperada()
        );

        // Enviar el JSON al servidor
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Obtener el código de respuesta del servidor
        int code = con.getResponseCode();
        return code == 200 || code == 201;

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
    @Override
    public List<Object> list() {
        try {
            URL url = new URL("http://localhost:8080/apiProject/projects");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<Project> proyectos = mapper.readValue(inputStream, new TypeReference<List<Project>>() {
            });
            return new ArrayList<>(proyectos);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /* private ProjectState obtenerEstadoDesdeBD(String estadoBD) {
        switch (estadoBD) {
            case "ACEPTADO":
                return new AceptadoState();
            case "EJECUCION":
                return new EnEjecucionState();
            case "RECHAZADO":
                return (ProjectState) new RechazadoState();
            case "RECIBIDO":
                return new RecibidoState();
            case "CERRADO":
                return new CerradoState();
            default:
                throw new IllegalArgumentException("Estado no reconocido: " + estadoBD);
        }
    }
     */
    private boolean conectar() {
        return false;
    }

    @Override
    public ProjectStatusResponse actualizarEstado(ProjectStatusRequest request) {
        try {
            URL url = new URL("http://localhost:8080/apiProject/projects/status");

            // Configurar la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Convertir el objeto ProjectStatusRequest a JSON
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(request);

            
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta 
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Convertir la respuesta JSON a ProjectStatusResponse
                return mapper.readValue(response.toString(), ProjectStatusResponse.class);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Object> getProjectsNit(String nit) {
        try {
            URL url = new URL("http://localhost:8080/apiProject/projectsCompany/" + nit);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();

            // Deserializamos la respuesta JSON a una lista de objetos genéricos
            List<ProjectRequestCompany> proyectos = mapper.readValue(inputStream, new TypeReference<List<ProjectRequestCompany>>() {
            });

            List<Project> projectList = new ArrayList<>();

            for (ProjectRequestCompany dto : proyectos) {
                Project project = new Project();
                project.setId(Long.valueOf(dto.getId().toString()));
                project.setNombre((String) dto.getNombre());
                project.setResumen((String) dto.getResumen());
                project.setDescripcion((String) dto.getDescripcion());
                project.setObjetivo((String) dto.getObjetivo());
                project.setTiempoMaximo((String) dto.getTiempoMaximo());
                project.setPresupuesto(dto.getPresupuesto());
                project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());    
                switch (dto.getEstadoTexto()) {
                    case "RECIBIDO":
                        project.setEstado(new ProjectState("RECIBIDO"));
                        break;
                    case "ACEPTADO":
                        project.setEstado(new ProjectState("ACEPTADO"));
                        break;
                    case "RECHAZADO":
                        project.setEstado(new ProjectState("RECHAZADO"));
                        break;
                    case "EN EJECUCION":
                        project.setEstado(new ProjectState("EN EJECUCION"));
                        break;
                    case "CERRADO":
                        project.setEstado(new ProjectState("CERRADO"));
                        break;
                    default:
                        throw new AssertionError();
                }

                projectList.add(project);
            }

            return new ArrayList<>(projectList);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Project found(Object idProject) {
        try {
            URL url = new URL("http://localhost:8080/apiProject/project/" + idProject); // Cambia el puerto si es necesario
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                try (InputStream is = con.getInputStream()) {
                    return mapper.readValue(is, Project.class);
                }
            } else {
                System.err.println("Error: código HTTP " + con.getResponseCode());
                return null;
            }
        } catch (IOException e) {
            System.err.println("Excepción al buscar proyecto: " + e.getMessage());
            return null;
        }
    }

}
