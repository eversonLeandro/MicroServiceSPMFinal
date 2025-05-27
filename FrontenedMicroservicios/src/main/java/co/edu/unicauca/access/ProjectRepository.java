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
import co.edu.unicauca.infra.adapter.ProjectRequestCompany;
import co.edu.unicauca.infra.dto.CommentRequest;
import co.edu.unicauca.infra.dto.JsonUtils;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
            URL url = new URL("http://localhost:8086/apiProject/project");
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
            URL url = new URL("http://localhost:8086/apiProject/projects");

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
            URL url = new URL("http://localhost:8086/apiProject/projects/status");

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
    public List<ProjectRequestCompany> getProjectsNit(String nit) {
        try {
            URL url = new URL("http://localhost:8086/apiProject/projectsCompany/" + nit);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();

            // Deserializamos la respuesta JSON a una lista de objetos genéricos
            List<ProjectRequestCompany> proyectos = mapper.readValue(inputStream, new TypeReference<List<ProjectRequestCompany>>() {
            });

            return proyectos;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Project found(Object idProject) {
        try {
            URL url = new URL("http://localhost:8086/apiProject/project/" + idProject); // Cambia el puerto si es necesario
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

    @Override
    public List<Map<String, String>> getCommentsByProject(int projectId) throws IOException {
        try {
            URL url = new URL("http://localhost:8086/api/projects/" + projectId + "/comments");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new IOException("Error HTTP: " + responseCode);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                if (response.length() == 0) {
                    return Collections.emptyList();
                }

                JsonArray jsonArray = JsonUtils.parseJsonArray(response.toString());
                return parseCommentList(jsonArray);
            }
        } catch (Exception e) {
            System.err.println("Error en getCommentsByProject: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean addComment(int projectId, int coordinatorId, String coordinatorName, String message) throws IOException {
        try {
            URL url = new URL("http://localhost:8086/api/projects/" + projectId + "/comments");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("coordinatorId", coordinatorId);
            jsonObject.addProperty("coordinatorName", coordinatorName);
            jsonObject.addProperty("message", message);

            String jsonInput = jsonObject.toString();

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 201) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    String response = br.readLine();
                    return true;
                }
            } else {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()))) {
                    String errorResponse = br.readLine();
                }
                return false;
            }
        } catch (Exception e) {
            System.err.println("Excepción en addComment: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Map<String, String>> parseCommentList(JsonArray jsonArray) {
        List<Map<String, String>> comments = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject json = element.getAsJsonObject();
            Map<String, String> comment = new HashMap<>();

            comment.put("coordinatorName", json.get("coordinatorName").getAsString());
            comment.put("message", json.get("message").getAsString());
            comment.put("timestamp", json.get("timestamp").getAsString());

            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Map<String, String> parseComment(JsonObject json) {
        Map<String, String> comment = new HashMap<>();
        comment.put("coordinatorName", json.get("coordinatorName").getAsString());
        comment.put("message", json.get("message").getAsString());
        comment.put("timestamp", json.get("timestamp").getAsString());
        return comment;
    }

}
