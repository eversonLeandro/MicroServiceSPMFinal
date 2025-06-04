package co.edu.unicauca.microserviceproject.domain.model.project;

import java.time.LocalDateTime;

public class Comment {

    private Integer id;
    private final String projectId;
    private final String texto;
    private final Long autorId;
    private final LocalDateTime fecha;

    public Comment(Integer id, String projectId, String texto, Long autorId, LocalDateTime fecha) {
        this.id = id;
        this.projectId = projectId;
        this.texto = texto;
        this.autorId = autorId;
        this.fecha = LocalDateTime.now();

    }
}
