/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.dto;

/**
 *
 * @author evers
 */
public class ProjectStatusRequest {

    private Long projectId;
    private String action;

    public ProjectStatusRequest() {

    }

    
    
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAction() {
        return action;
    }

    
    public void setAction(String action) {
        this.action = action;
    }
    

}
