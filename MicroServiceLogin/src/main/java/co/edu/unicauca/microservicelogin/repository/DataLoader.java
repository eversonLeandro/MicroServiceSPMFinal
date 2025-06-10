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
    @Autowired
    private UsuarioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("QEUMANDO DATOS DE USUARIOS");
        repository.deleteAll();
        if (usuarioService.findAll().isEmpty()) {
            UsuarioRequest admin = new UsuarioRequest();
            admin.setId(24);
            admin.setUsername("admin");
            admin.setContrasenia("admin123");
            admin.setEmail("yisus0816z@gmail.com");
            admin.setRol("coordinator");
            usuarioService.save(admin);

            UsuarioRequest user1 = new UsuarioRequest();
            user1.setId(10);
            user1.setUsername("estudiante1");
            user1.setContrasenia("0001");
            user1.setEmail("estudiante1@gmail.com");
            user1.setRol("student");
            usuarioService.save(user1);

            UsuarioRequest user2 = new UsuarioRequest();
            user2.setId(30);
            user2.setUsername("meradata");
            user2.setContrasenia("clave");
            user2.setEmail("yisus0816z1@gmail.com");
            user2.setRol("company");
            usuarioService.save(user2);

            UsuarioRequest user3 = new UsuarioRequest();
            user3.setId(40);
            user3.setUsername("Camila");
            user3.setContrasenia("0816");
            user3.setEmail("camila@unicauca.edu.co");
            user3.setRol("student");
            usuarioService.save(user3);

            UsuarioRequest user4 = new UsuarioRequest();
            user4.setId(50);
            user4.setUsername("InnovativeTechSAS");
            user4.setContrasenia("900123456");
            user4.setEmail("innovative@gmail.com");
            user4.setRol("company");
            usuarioService.save(user4);

            UsuarioRequest user5 = new UsuarioRequest();
            user5.setId(60);
            user5.setUsername("HealthPlus");
            user5.setContrasenia("1002");
            user5.setEmail("health@gmail.com");
            user5.setRol("company");
            usuarioService.save(user5);

            UsuarioRequest user6 = new UsuarioRequest();
            user6.setId(70);
            user6.setUsername("EduWorld");
            user6.setContrasenia("1003");
            user6.setEmail("edu@gmail.com");
            user6.setRol("company");
            usuarioService.save(user6);



            System.out.println("⚡ Datos iniciales cargados: usuarios quemados.");
        }
    }
}
