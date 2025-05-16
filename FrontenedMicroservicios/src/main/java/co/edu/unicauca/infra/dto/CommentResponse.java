/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.dto;

/**
 *
 * @author RoLoNeGaTiVo
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentResponse {
    private String coordinatorName;
    private String message;
    private String timestamp;
    
    public CommentResponse(String coordinatorName, String message, LocalDateTime timestamp) {
        this.coordinatorName = coordinatorName;
        this.message = message;
        this.timestamp = timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    


}
