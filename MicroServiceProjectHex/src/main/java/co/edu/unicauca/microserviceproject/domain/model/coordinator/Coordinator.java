package co.edu.unicauca.microserviceproject.domain.model.coordinator;


import co.edu.unicauca.microserviceproject.domain.model.coordinator.VO.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coordinator {

    private Long codCor;
    private String estado;
    private Email email;


}
