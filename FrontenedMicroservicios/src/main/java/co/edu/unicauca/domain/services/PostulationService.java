/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.access.PostulationRepository;
import co.edu.unicauca.domain.entities.Postulation;


/**
 *
 * @author RoLoNeGaTiVo
 */
public class PostulationService {

   private PostulationRepository repo;


    public PostulationService(PostulationRepository repository) {
          this.repo = repository;
    }

    public boolean savePostulation(Postulation postulation) {
           return repo.save(postulation);
    }
}
