/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Project;
import java.util.HashMap;
import java.util.Map;
import co.edu.unicauca.infra.adapter.ProjectRequestCompany;
import co.edu.unicauca.infra.dto.ProjectStatusRequest;
import co.edu.unicauca.infra.dto.ProjectStatusResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;

public interface IProjectRepository extends IRepository {

    List<ProjectRequestCompany> getProjectsNit(String nit);

    ProjectStatusResponse actualizarEstado(ProjectStatusRequest p);

    List<Map<String, String>> getCommentsByProject(int projectId) throws IOException;

    boolean addComment(int projectId, int coordinatorId, String coordinatorName, String message) throws IOException;

    List<Map<String, String>> parseCommentList(JsonArray jsonArray);

    Map<String, String> parseComment(JsonObject json);
}
