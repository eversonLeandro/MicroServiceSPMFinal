package co.edu.unicauca.infra;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.services.PostulationService;
import co.edu.unicauca.domain.services.StudentService;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author evers
 */
public class PostularseContext implements IButtonContext {

    private JButton button;
    private JFrame parent;
    private IFrameEventListener listener;
    private PostulationService postulaciones;
    private StudentService serviceStudent;
    private Project project;
    private String username;
    private JTable table;
    private int row;

    public PostularseContext(JTable table,JFrame parent, IFrameEventListener listener,
            String username,StudentService serviceStudent ,PostulationService postulaciones_){
        this.parent = parent;
        this.table = table;        
        this.listener = listener;
        this.username = username;  
        this.serviceStudent = serviceStudent;
        this.postulaciones = postulaciones_;
    }
 
    /**
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    /**
     * @return the parent
     */
    public JFrame getParent() {
        return parent;
    }

    /**
     * @return the listener
     */
    public IFrameEventListener getListener() {
        return listener;
    }

    /**
     * @return the postulaciones
     */
    public PostulationService getPostulaciones() {
        return postulaciones;
    }

    /**
     * @return the serviceStudent
     */
    public StudentService getServiceStudent() {
        return serviceStudent;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

  

 
}
