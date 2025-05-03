package co.edu.unicauca.microservicelogin.repository;

import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        // 1) Comprobar si ya hay datos para no duplicar
        if (usuarioService.findAll().isEmpty()) {
            // 2) Crear y guardar usuarios “quemados”
            UsuarioRequest admin = new UsuarioRequest();
            admin.setId(24);
            admin.setUsername("admin");
            admin.setContrasenia("admin123");
            admin.setEmail("admin@admin.com");
            admin.setRol("ADMIN");
            usuarioService.save(admin);

            UsuarioRequest user1 = new UsuarioRequest();
            user1.setId(10);
            user1.setUsername("estudiante1");
            user1.setContrasenia("clave1");
            user1.setEmail("estudiante1@gmail.com");
            user1.setRol("STUDENT");
            usuarioService.save(user1);

            UsuarioRequest user2 = new UsuarioRequest();
            user2.setId(30);
            user2.setUsername("meradata");
            user2.setContrasenia("clave");
            user2.setEmail("mera@gmail.com");
            user2.setRol("COMPANY");


            usuarioService.save(user2);
            UsuarioRequest user3 = new UsuarioRequest();
            user3.setId(40);
            user3.setUsername("Camila");
            user3.setContrasenia("0816");
            user3.setEmail("camila@unicauca.edu.co");
            user3.setRol("STUDENT");
            usuarioService.save(user3);



            System.out.println("⚡ Datos iniciales cargados: usuarios quemados.");
        }
    }
}
