package co.edu.unicauca.microserviceproject.infra.states;

public class MessageResponse {
    private ProjectState estado;
    private String message;

    public MessageResponse(ProjectState estado, String message) {
        this.estado = estado;
        this.message = message;
    }

    public ProjectState getEstado() {
        return estado;
    }

    public String getMessage() {
        return message;
    }
}
