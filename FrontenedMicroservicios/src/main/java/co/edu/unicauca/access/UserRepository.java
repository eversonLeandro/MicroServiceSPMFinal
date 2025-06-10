
package co.edu.unicauca.access;

import com.google.gson.Gson;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.interfaces.IRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Brayan
 */
public class UserRepository implements IRepository {


    public UserRepository() {

    }

    @Override
    public boolean save(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User found(Object user) {
        String usuario = (String) user;

        try {
            URL url = new URL("http://localhost:8081/api/usuarios/validar"); // <-- API Gateway
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            // Recuperar el token de sesión y agregarlo al header
            String token = SessionManager.getToken();
            if (token != null) {
                con.setRequestProperty("Authorization", "Bearer " + token);
            }

            con.setDoOutput(true);

            // Construir el JSON para enviar
            String jsonInputString = "{\"username\": \"" + usuario + "\"}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            if (code == 200) {
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
                System.err.println("Código HTTP inesperado: " + code);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
