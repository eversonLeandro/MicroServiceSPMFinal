package co.edu.unicauca.microserviceproject.repository;

import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Coordinator;
import co.edu.unicauca.microserviceproject.entities.Postulation;
import co.edu.unicauca.microserviceproject.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        companyRepository.save(company);

        // Crear coordinador

        Coordinator coordinator = new Coordinator();
        coordinator.setCodCor(Long.valueOf("123"));
        coordinator.setGmail("lucia.ramirez@universidad.edu.co");
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

        // Crear postulaciones y agregarlas al proyecto
        Date currentDate = new Date(System.currentTimeMillis());
        Postulation post1 = new Postulation(Long.valueOf("11"),Long.valueOf("123456"), project.getId(), new Timestamp(currentDate.getTime()));
        Postulation post2 = new Postulation(Long.valueOf("22"),Long.valueOf("765432"), project.getId(), new Timestamp(currentDate.getTime()));

        System.out.println(post1.getIdPostulation());
        List<Postulation> postulaciones = new ArrayList<>();
        postulaciones.add(post1);
        postulaciones.add(post2);

        project.setPostulations(postulaciones);

        // Guardar proyecto y sus postulaciones
        postulationRepository.saveAll(postulaciones);
        projectRepository.save(project); // Postulations se guardan si hay cascade, si no:

    }
}
