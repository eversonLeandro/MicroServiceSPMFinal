package co.edu.unicauca.domain.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AuthService {
    public static String getTokenFromKeycloak(String username, String password) {
    try {
        URL url = new URL("http://localhost:8080/realms/MicroserviceSPM/protocol/openid-connect/token");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);

        String body = "grant_type=password" +
                     "&client_id=system-desktop" +
                     "&client_secret=pQZqPhgwKx32hFTuoMxmj638FrhoN5dG" +
                     "&username=" + URLEncoder.encode(username, "UTF-8") +
                     "&password=" + URLEncoder.encode(password, "UTF-8") +
                     "&scope=openid";

        // Debug: imprimir el body que se envía
        System.out.println("Request body: " + body);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == 200) {
            try (InputStream input = con.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> response = mapper.readValue(input, 
                    new TypeReference<Map<String, Object>>() {});
                return response.get("access_token").toString();
            }
        } else {
            // Leer el cuerpo del error
            try (InputStream error = con.getErrorStream()) {
                if (error != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> errorResponse = mapper.readValue(error, 
                        new TypeReference<Map<String, Object>>() {});
                    System.err.println("Keycloak error: " + errorResponse);
                }
            }
            return null;
        }
    } catch (Exception e) {
        System.err.println("Exception getting token:");
        e.printStackTrace();
        return null;
    }
}
}
