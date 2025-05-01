package co.edu.unicauca.microserviceproject.infra.dto;

import co.edu.unicauca.microserviceproject.entities.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapperCompany {
    ProjectRequestCompany dto(Project project);

}
