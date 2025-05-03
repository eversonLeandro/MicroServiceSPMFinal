package co.edu.unicauca.microserviceproject.infra.dto;

import co.edu.unicauca.microserviceproject.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapperCompany {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "resumen", target = "resumen")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "objetivo", target = "objetivo")
    @Mapping(source = "tiempoMaximo", target = "tiempoMaximo")
    @Mapping(source = "presupuesto", target = "presupuesto")
    @Mapping(source = "fechaEntregadaEsperada", target = "fechaEntregadaEsperada")
    @Mapping(source = "company.nit", target = "nitCompany")
    @Mapping(source = "estadoTexto", target = "estadoTexto")// Asegura que company no sea null
    ProjectRequestCompany dto(Project project);


}
