/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;

//import co.edu.unicauca.view.GUIInfoProject;

import co.edu.unicauca.view.GUIInfoProject;


/**
 *
 * @author evers
 */
public class ViewProjectAction implements IButtonAction<verProyectPostuladoContext>{
    
    @Override
    public void execute(verProyectPostuladoContext context) {
        String projectId = (String) context.getTable().getValueAt(context.getRow(), 5);
        GUIInfoProject instance = new GUIInfoProject(context.getParent(), context.getListener(), projectId, context.getProjectService());
        instance.setVisible(true);
    }


}
