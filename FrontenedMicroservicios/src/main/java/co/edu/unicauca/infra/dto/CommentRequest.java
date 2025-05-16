/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.dto;

/**
 *
 * @author RoLoNeGaTiVo
 */
public class CommentRequest {
    private int coordinatorId;
    private String coordinatorName;
    private String message;
    
    public CommentRequest(){}
    public CommentRequest(int coordinatorId, String coordinatorName, String message) {
        this.coordinatorId = coordinatorId;
        this.coordinatorName = coordinatorName;
        this.message = message;
    }
    
    
    public int getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(int coordinatorId) { this.coordinatorId = coordinatorId; }
    
    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
