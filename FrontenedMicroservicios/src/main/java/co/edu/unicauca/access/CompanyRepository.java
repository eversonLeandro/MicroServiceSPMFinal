/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.access;

import co.edu.unicauca.interfaces.ICompanyRepository;
import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.infra.Messages;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
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
            URL url = new URL("http://localhost:8088/apiCompanies/guardar");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
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
        try {
            URL url = new URL("http://localhost:8088/apiCompanies"); // Endpoint para obtener todas las empresas

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer el InputStream de la respuesta
            InputStream inputStream = connection.getInputStream();

            // Usar ObjectMapper para mapear la respuesta JSON a una lista de Company
            ObjectMapper mapper = new ObjectMapper();
            List<Company> companies = mapper.readValue(inputStream, new TypeReference<List<Company>>() {
            });

            return new ArrayList<>(companies);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // En caso de error, retornar null
        }
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
