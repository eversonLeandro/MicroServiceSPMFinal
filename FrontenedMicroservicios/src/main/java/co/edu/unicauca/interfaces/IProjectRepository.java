/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.infra.dto.ProjectStatusRequest;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import java.util.List;

public interface IProjectRepository extends IRepository{

    List<Object> getProjectsNit(String nit);
    ProjectStatusResponse actualizarEstado(ProjectStatusRequest p);

}
