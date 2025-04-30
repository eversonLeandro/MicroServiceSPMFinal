/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.User;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Brayan
 */
public class UserService {

    private final String API_URL = "http://localhost:8080/api/usuarios";

    public User obtnerUsuario(String username, String contrasenia) {
       
        return autenticarUsuario(username, contrasenia);
    }

    private User autenticarUsuario(String usuario, String contrasenia) {
        try {
            URL url = new URL("http://localhost:8081/api/usuarios/validar");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Construir el JSON para enviar
            String jsonInputString = "{\"username\": \"" + usuario + "\", \"contrasenia\": \"" + contrasenia + "\"}";

            // Enviar la petición
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            if (code == 200) { // HTTP OK
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Convertir JSON a objeto User
                Gson gson = new Gson();
                User usuarioResultante = gson.fromJson(response.toString(), User.class);
                return usuarioResultante;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
