package co.edu.unicauca.companymicroservice.Infra.Mappers;

import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Infra.DTO.UsuarioRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioRequest obteneruser(CompanyDTO dto){
        UsuarioRequest usuarioRequest=new UsuarioRequest();
        usuarioRequest.setId(Long.valueOf(dto.getNit()));
        usuarioRequest.setUsername(dto.getNombre());
        usuarioRequest.setEmail(dto.getEmail());
        usuarioRequest.setContrasenia(String.valueOf(dto.getNit()));
        usuarioRequest.setRol(("company"));
        return usuarioRequest;
    }
}
