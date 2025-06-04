/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.view.GUIDetalleProyecto;

/**
 *
 * @author evers
 */
public class postularseAction implements IButtonAction<PostularseContext> {

    @Override
    public void execute(PostularseContext context) {

        Project project = (Project) context.getTable().getValueAt(context.getRow(), 6);
        GUIDetalleProyecto instance = new GUIDetalleProyecto(context.getParent(),
                context.getListener(), project, context.getUsername(), context.getPostulaciones(), context.getServiceStudent());

        instance.setVisible(true);
    }

}
