package co.edu.unicauca.microserviceproject.adapter.in;

import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllCompanies;
import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllProjects;
import co.edu.unicauca.microserviceproject.aplication.port.in.IGetAllProjectsWithCompany;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetAllProjectWithCompanyUseCase implements IGetAllProjectsWithCompany {

    private final IProjectRepositoryPort iProjectRepositoryPort;
    private final IGetAllCompanies iGetAllCompanies;

    @Override
    public List<ProjectDto> getAllProjectsWithCompany() {
        List<Project> projects = iProjectRepositoryPort.getAllProjects();
        List<Company> companies = iGetAllCompanies.getAllCompanies();

        Map<Long, String> companyNames = companies.stream()
                .collect(Collectors.toMap(Company::getNit, Company::getNombre));

        return projects.stream()
                .map(p -> new ProjectDto(
                        p.getIdProject(),
                        p.getCompanyId(),
                        p.getNombre(),
                        p.getResumen(),
                        p.getDescripcion(),
                        p.getObjetivo(),
                        p.getTiempoMaximo(),
                        p.getPresupuesto().getValor().toString(),
                        p.getFechaEntregaEsperada().getFechaFormateada(),
                        p.getEstado().getEstado(),
                        p.getPeriodoAcademico().getPeriodo(),
                        companyNames.get(p.getCompanyId())
                ))
                .collect(Collectors.toList());
    }
}
