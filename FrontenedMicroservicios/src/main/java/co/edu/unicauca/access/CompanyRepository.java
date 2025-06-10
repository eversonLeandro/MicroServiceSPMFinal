/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.access;

import co.edu.unicauca.interfaces.ICompanyRepository;
import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.infra.Messages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.gson.internal.bind.TypeAdapters.URL;

/**
 *
 * @author Brayan
 */
public class CompanyRepository implements ICompanyRepository {

    public CompanyRepository() {

    }

    @Override
    public boolean save(Object companyObject) {
        Company company = (Company) companyObject;

        try {
            URL url = new URL("http://localhost:8081/apiCompanies/guardar");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            // Recuperar el token de sesión y agregarlo al header
            String token = SessionManager.getToken();
            System.out.println("el token es: " + token);
            if (token != null) {
                con.setRequestProperty("Authorization", "Bearer " + token);
            }
            con.setDoOutput(true);

            // Crear el JSON manualmente
            String jsonInputString = String.format("""
        {
            "nit": "%s",
            "nombre": "%s",
            "email": "%s",
            "telefono": "%s",
            "nombrecontaccto": "%s",
            "apellido": "%s",
            "sector": "%s",
            "cargo": "%s",
            "estado": "%s"
        }
        """,
                    company.getNit(),
                    company.getNombre(),
                    company.getEmail(),
                    company.getTelefono(),
                    company.getNombrecontaccto(),
                    company.getApellido(),
                    company.getSector(),
                    company.getCargo(),
                    company.getEstado());

            // Enviar el JSON al servidor
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener el código de respuesta del servidor
            int code = con.getResponseCode();
            System.out.println("Código de respuesta: " + code);

// Si el código no es exitoso, imprime el cuerpo del error
            if (code != 200 && code != 201) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Respuesta de error: " + response.toString());
                }
            }
            return code == 200 || code == 201;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
    public List<Object> list() {
        return null;
    }

    public Object found(Object usename) {

        return null;
    }

    public Company getCompanyWithUser(String username) {

        return null;
    }

    private boolean conectar() {
        return true;

    }

}
