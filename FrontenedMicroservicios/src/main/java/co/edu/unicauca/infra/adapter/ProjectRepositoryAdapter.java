/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.adapter;

import co.edu.unicauca.access.ProjectRepository;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.infra.ProjectState;
import co.edu.unicauca.interfaces.IProjectRepository;
import co.edu.unicauca.interfaces.IRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Brayan
 */
public class ProjectRepositoryAdapter  {

    private ProjectRepository repository;

    public ProjectRepositoryAdapter(ProjectRepository repository) {
        this.repository = repository;
    }

    public  List<Project> proyectosadaptados(List<ProjectRequestCompany> dtos) {
        
        return dtos.stream()
                .map(ProjectRepositoryAdapter::toDomain)
                .collect(Collectors.toList());
    }
    
    private static Project toDomain(ProjectRequestCompany dto) {
        Project p = new Project();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setResumen(dto.getResumen());
        p.setDescripcion(dto.getDescripcion());
        p.setObjetivo(dto.getObjetivo());
        p.setTiempoMaximo(dto.getTiempoMaximo());
        p.setPresupuesto(dto.getPresupuesto());
        p.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());

        switch (dto.getEstadoTexto()) {
            case "RECIBIDO":
                p.setEstado(new ProjectState("RECIBIDO"));
                break;
            case "ACEPTADO":
                p.setEstado(new ProjectState("ACEPTADO"));
                break;
            case "RECHAZADO":
                p.setEstado(new ProjectState("RECHAZADO"));
                break;
            case "EN EJECUCION":
                p.setEstado(new ProjectState("EN EJECUCION"));
                break;
            case "CERRADO":
                p.setEstado(new ProjectState("CERRADO"));
                break;
            default:
                throw new IllegalArgumentException("Estado no válido: " + dto.getEstadoTexto());
        }

        return p;
    }

    public List<Project> getProjectsNit(String nit) {
       List<ProjectRequestCompany> dtos = repository.getProjectsNit(nit);
        // 2) delego la conversión DTO → Domain
        return proyectosadaptados(dtos);
    }

}
