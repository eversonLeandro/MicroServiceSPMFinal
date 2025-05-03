/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra;

import co.edu.unicauca.access.Factory;
import co.edu.unicauca.access.PostulationRepository;
import co.edu.unicauca.access.ProjectRepository;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.CompanyService;
import co.edu.unicauca.domain.services.PostulationService;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.domain.services.StudentService;
import co.edu.unicauca.interfaces.IFrameFactory;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.view.GUIGestionSoftwareEmpresa;
import co.edu.unicauca.view.GUIGestionSofwareCoordination;
import co.edu.unicauca.view.GUIGestionSottwareStudent;
import javax.swing.JFrame;

/**
 *
 * @author Brayan
 */
public class FrameFactory implements IFrameFactory {

    @Override
    public JFrame createFrame(User user) {
        switch (user.getRol()){
             case "STUDENT":
                IRepository repositorys = Factory.getInstance().getRepository("student");
                IRepository repositoryp = Factory.getInstance().getRepository("project");
                IRepository repositorypos=Factory.getInstance().getRepository("postulation");
                ProjectService projectServi = new ProjectService((ProjectRepository) repositoryp);
                StudentService studentService = new StudentService(repositorys);
                PostulationService postulationService = new PostulationService((PostulationRepository) repositorypos);
                return new GUIGestionSottwareStudent(projectServi, user, studentService,postulationService);
            case "ADMIN":
                IRepository repository2 = Factory.getInstance().getRepository("project");
                ProjectService projectService = new ProjectService((ProjectRepository) repository2);
                return new GUIGestionSofwareCoordination(projectService, user);
            case "COMPANY":
                IRepository repository3 = Factory.getInstance().getRepository("company");
                IRepository repository4 = Factory.getInstance().getRepository("project");
                CompanyService companyService = new CompanyService(repository3);
                ProjectService projectService1 = new ProjectService((ProjectRepository) repository4);
                return new GUIGestionSoftwareEmpresa(companyService, projectService1, user);
                
                
                

            // Agrega otros roles según necesites
            default:
                return null;
        }
        }

    }

