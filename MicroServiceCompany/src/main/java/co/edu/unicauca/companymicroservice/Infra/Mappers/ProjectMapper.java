package co.edu.unicauca.companymicroservice.Infra.Mappers;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProjectMapper {



    public static Project projectToEntity(ProjectRequestCompany dto, Company company) {
        Project project = new Project();
        project.setIdProject(dto.getId());
        project.setCompany(company);
        project.setTitulo(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoEst(dto.getTiempoMaximo());
        project.setPresupuesto(BigDecimal.valueOf(Long.valueOf(dto.getPresupuesto())));
        project.setFechaEntregaEsperada(dto.getFechaEntregadaEsperada());
        project.setPeriodoAcademico(dto.getPeriodoAcademico());
        return project;
    }

}
