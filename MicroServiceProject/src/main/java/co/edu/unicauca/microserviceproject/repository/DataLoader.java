package co.edu.unicauca.microserviceproject.repository;

import co.edu.unicauca.microserviceproject.entities.*;
import co.edu.unicauca.microserviceproject.infra.states.EnEjecucionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;
    @Autowired
    PostulationRepository postulationRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos anteriores
        postulationRepository.deleteAll();
        projectRepository.deleteAll();
        coordinatorRepository.deleteAll();
        companyRepository.deleteAll();

        // Crear empresa
        Company company = new Company();
        company.setNit(Long.valueOf("900123456"));
        company.setNombre("Innovative Tech S.A.S.");
        company.setEmail("yisus0816z1@gmail.com");
        companyRepository.save(company);

        // Crear empresa
        Company company1 = new Company();
        company1.setNit(Long.valueOf("30"));
        company1.setNombre("meradata");
        company1.setEmail("yisus0816z1@gmail.com");
        companyRepository.save(company1);

        // Crear coordinador

        Coordinator coordinator = new Coordinator();
        coordinator.setCodCor(Long.valueOf("123"));
        coordinator.setGmail("yisus0816z@gmail.com");
        coordinatorRepository.save(coordinator);

        // Crear proyecto
        Project project = new Project();
        project.setNombre("App de Gestión de Inventarios");
        project.setResumen("Una aplicación para gestionar inventarios de pequeñas empresas.");
        project.setDescripcion("Sistema de escritorio con Java y MySQL.");
        project.setObjetivo("Optimizar la trazabilidad de productos en bodegas.");
        project.setTiempoMaximo("6 meses");
        project.setPresupuesto("8.000.000");
        project.setFechaEntregadaEsperada("2025-10-30");
        project.setCompany(company);
        project.setCoordinator(coordinator);
        project.setPeriodoAcademico("2025-1");

        Project project2 = new Project();
        project2.setNombre("MeraSoftware");
        project2.setResumen("Plataforma que enseña técnicas y métodos para mejorar el rendimiento en videojuegos.");
        project2.setDescripcion("Aplicación web con inteligencia artificial que analiza patrones de juego y sugiere mejoras personalizadas.");
        project2.setObjetivo("Ayudar a jugadores a optimizar su tiempo de juego y aumentar su ranking competitivo.");
        project2.setTiempoMaximo("4 meses");
        project2.setPresupuesto("5.000.000");
        project2.setFechaEntregadaEsperada("2025-09-15");
        project2.setCompany(company1);
        project2.setCoordinator(coordinator);
        project2.setPeriodoAcademico("2024-2");
        project2.setEstado(new EnEjecucionState());
        project2.setEstadoTexto("EN EJECUCION");
        // Crear postulaciones y agregarlas al proyecto
        LocalDateTime currentDate = LocalDateTime.now();
        Postulation post1 = new Postulation(Long.valueOf("11"),Long.valueOf("123456"), project.getId(), currentDate);
        Postulation post2 = new Postulation(Long.valueOf("22"),Long.valueOf("765432"), project.getId(), currentDate);

        List<Postulation> postulaciones = new ArrayList<>();
        postulaciones.add(post1);
        postulaciones.add(post2);

        project.setPostulations(postulaciones);

        // Guardar proyecto y sus postulaciones
        postulationRepository.saveAll(postulaciones);
        projectRepository.save(project); // Postulations se guardan si hay cascade, si no:
        projectRepository.save(project2);

        Comment comment1 = new Comment(project.getId().intValue(), 123, "Lucía Ramírez",
                "Este proyecto tiene un gran potencial. Necesitamos revisar el presupuesto.");
        commentRepository.save(comment1);

        Comment comment2 = new Comment(project.getId().intValue(), 123, "Lucía Ramírez",
                "He revisado los requisitos y todo parece estar en orden.");
        commentRepository.save(comment2);

        Comment comment3 = new Comment(project2.getId().intValue(), 123, "Lucía Ramírez",
                "El enfoque en IA para análisis de juegos es innovador.");
        commentRepository.save(comment3);

    }
}
