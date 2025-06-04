package co.edu.unicauca.microserviceproject.aplication.port.in;

import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import org.w3c.dom.Comment;

public interface ICrateComment {
    Comment saveComment(Project project);
}
