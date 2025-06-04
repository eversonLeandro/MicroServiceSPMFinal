package co.edu.unicauca.microserviceproject.domain.model.company;


import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Company {

    private final Long nit;
    private String nombre;
    private Email email;

}
