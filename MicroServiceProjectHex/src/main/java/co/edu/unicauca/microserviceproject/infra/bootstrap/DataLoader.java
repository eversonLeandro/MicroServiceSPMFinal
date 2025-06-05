package co.edu.unicauca.microserviceproject.infra.bootstrap;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CompanyJpaRepository;
import co.edu.unicauca.microserviceproject.adapter.out.repository.CoordinatorJpaRepository;
import co.edu.unicauca.microserviceproject.adapter.out.repository.ProjectJpaRepository;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import co.edu.unicauca.microserviceproject.infra.entities.CoordinatorEntity;
import co.edu.unicauca.microserviceproject.infra.entities.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ProjectJpaRepository projectRepository;
    @Autowired
    CompanyJpaRepository companyRepository;
    @Autowired
    CoordinatorJpaRepository coordinatorRepository;


    @Override
    public void run(String... args) throws Exception {
        // Limpiar datos anteriores
        //postulationRepository.deleteAll();
        projectRepository.deleteAll();
        //coordinatorRepository.deleteAll();
        companyRepository.deleteAll();


        CompanyEntity company = CompanyEntity.builder()
                .nit(900123456L)
                .nombre("Innovative Tech S.A.S.")
                .email("eversonl2003@gmail.com")
                .build();
        companyRepository.save(company);

        CompanyEntity company1 = CompanyEntity.builder()
                .nit(30L)
                .nombre("meradata")
                .email("yisus0816z1@gmail.com")
                .build();
        companyRepository.save(company1);

        CompanyEntity company2 = CompanyEntity.builder()
                .nit(10L)
                .nombre("CantinaJesus")
                .email("bhmeneses2@gmail.com")
                .build();
        companyRepository.save(company2);

        CompanyEntity company3 = CompanyEntity.builder()
                .nit(20L)
                .nombre("CanchasCubo")
                .email("juanesteban.meramorales.7@gmail.com")
                .build();
        companyRepository.save(company3);

        // Crear coordinador

        CoordinatorEntity coordinator = new CoordinatorEntity(Long.valueOf("24"), "ACTIVO", "yisus0816z@gmail.com");
        coordinatorRepository.save(coordinator);

        // Crear proyecto
        ProjectEntity project = new ProjectEntity(
                900123456L,                      // NIT de la empresa
                24L,                          // ID del coordinador
                "App de Gestión de Inventarios",
                "Una aplicación para gestionar inventarios de pequeñas empresas.",
                "Sistema de escritorio con Java y MySQL.",
                "Optimizar la trazabilidad de productos en bodegas.",
                "6 meses",
                "8000000",
                "30/10/2025",
                "RECIBIDO",
                "2025-1"
        );

        ProjectEntity project2 = new ProjectEntity(
                30L,                              // NIT de la empresa company1
                24L,                             // ID del coordinador
                "MeraSoftware",
                "Plataforma que enseña técnicas y métodos para mejorar el rendimiento en videojuegos.",
                "Aplicación web con inteligencia artificial que analiza patrones de juego y sugiere mejoras personalizadas.",
                "Ayudar a jugadores a optimizar su tiempo de juego y aumentar su ranking competitivo.",
                "4 meses",
                "5000000",                       // presupuesto como String sin puntos
                "15/09/2025",                   // fecha como String (o LocalDate si tu clase lo usa así)
                "EN EJECUCION",                   // estado textual
                "2024-2"
        );
        ProjectEntity project3 = new ProjectEntity(
                10L,
                24L,
                "Sistema de Control de Asistencia con Reconocimiento Facial",
                "Aplicación para registrar asistencia estudiantil mediante reconocimiento facial.",
                "Sistema de escritorio en Java usando OpenCV y base de datos MySQL.",
                "Reducir el tiempo y errores en el control manual de asistencia.",
                "5 meses",
                "6200000",
                "20/08/2025",
                "RECIBIDO",
                "2025-1"
        );
        ProjectEntity project4 = new ProjectEntity(
                10L,
                24L,
                "Plataforma de Gestión de Prácticas Empresariales",
                "Sistema para gestionar postulaciones, seguimientos y evaluaciones de prácticas.",
                "Aplicación web con Java, Spring y MySQL.",
                "Facilitar la vinculación entre estudiantes y empresas.",
                "6 meses",
                "7500000",
                "10/10/2025",
                "RECIBIDO",
                "2025-1"

        );

        ProjectEntity project5 = new ProjectEntity(
                20L,
                24L,
                "Aplicación de Apoyo para Personas con Discapacidad Visual",
                "Asistente virtual con comandos por voz para la navegación en entornos urbanos.",
                "Aplicación móvil desarrollada en Kotlin con soporte GPS y reconocimiento de voz.",
                "Mejorar la autonomía de personas con discapacidad visual.",
                "4 meses",
                "6800000",
                "05/09/2025",
                "RECIBIDO",
                "2024-2"
        );

        ProjectEntity project6 = new ProjectEntity(
                30L,
                24L,
                "Sistema de Evaluación de Desempeño Docente",
                "Herramienta digital para evaluar y retroalimentar el desempeño de los profesores.",
                "Sistema web desarrollado en Java con Spring Boot y reportes dinámicos.",
                "Optimizar la toma de decisiones académicas a través de evaluaciones automatizadas.",
                "5 meses",
                "5900000",
                "10/08/2025",
                "RECIBIDO",
                "2025-1"
        );

        ProjectEntity project7 = new ProjectEntity(
                900123456L,
                24L,
                "Red Social Académica para Intercambio de Conocimientos",
                "Plataforma para que estudiantes y docentes compartan artículos, tutoriales y foros.",
                "Aplicación web en Java con Spring y MySQL, integrando foros y sistema de reputación.",
                "Fomentar el aprendizaje colaborativo y la publicación de contenido académico.",
                "6 meses",
                "8100000",
                "01/11/2025",
                "RECIBIDO",
                "2025-1"
        );
        projectRepository.save(project);
        projectRepository.save(project2);
        projectRepository.save(project3);
        projectRepository.save(project4);
        projectRepository.save(project5);
        projectRepository.save(project6);
        projectRepository.save(project7);

    }
}
