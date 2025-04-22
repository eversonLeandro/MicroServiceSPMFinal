package co.edu.unicauca.microserviceproject.service;

import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequest;
import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Coordinator;
import co.edu.unicauca.microserviceproject.entities.Postulation;
import co.edu.unicauca.microserviceproject.entities.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.CoordinatorRepository;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;
    @Autowired
    PostulationRepository postulationRepository;



    public List<Project> findAll() throws Exception {
        try {
            return projectRepository.findAll();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Project findById(Long id) throws Exception {
        return projectRepository.findById(id).get();
    }

    @Transactional
    public Project createProject(ProjectRequest dto) throws Exception {

        Project project = new Project();
        project.setNombre(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoMaximo(dto.getTiempoMaximo());
        project.setPresupuesto(dto.getPresupuesto());
        project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());

        Optional<Company> company = companyRepository.findById(dto.getNitCompany());
        if (company.isEmpty()) {
            throw new IllegalAccessException("El usuario con ID " + dto.getNitCompany() + " no existe");
        }
        project.setCompany(company.get());

        Optional<Coordinator> coordinator = coordinatorRepository.findById(dto.getCodCor());
        if (coordinator.isEmpty()) {
            throw new IllegalAccessException("El usuario con ID " + dto.getCodCor() + " no existe");
        }
        project.setCoordinator(coordinator.get());

        List<Postulation> postulaciones = new ArrayList<>();
        for (Long id : dto.getIdPostulaciones()) {
            Optional<Postulation> postulation = postulationRepository.findById(id);
            if (postulation.isEmpty()) {
                throw new IllegalArgumentException("La postulación con ID " + id + " no existe");
            }
            postulaciones.add(postulation.get());
        }
        project.setPostulations(postulaciones);

        return projectRepository.save(project);
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }


}
