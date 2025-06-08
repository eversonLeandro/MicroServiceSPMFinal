package co.edu.unicauca.microservicelogin.controller;

import co.edu.unicauca.microservicelogin.entities.User;
import co.edu.unicauca.microservicelogin.infra.dto.UsuarioRequest;
import co.edu.unicauca.microservicelogin.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class    UsuarioController {

    @Autowired
    private UsuarioService service;
    ModelMapper modelMapper= new ModelMapper();
    @PostMapping("/validar")
    public ResponseEntity<User> validarUsuario(@RequestBody UsuarioRequest loginRequest) {
        return service.findByUsername(loginRequest.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping("/crearusuario")
    public ResponseEntity<User> createUser(
            @RequestBody UsuarioRequest newUser
    ) {
        User saved = service.save(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(
            @PathVariable String username,
            @RequestBody UsuarioRequest updatedData
    ) {
        Optional<User> opt = service.findByUsername(username);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User existing = opt.get();
        UsuarioRequest userdto=modelMapper.map(existing,UsuarioRequest.class);
        existing.setContrasenia(updatedData.getContrasenia());
        existing.setRol(updatedData.getRol());
        User updated = service.save(userdto);
        return ResponseEntity.ok(updated);    // (10)
    }
    @GetMapping("/obtnerusuarios")
    public ResponseEntity<List<User>> getAllUsuarios() throws Exception {
        List<User> usuarioss = service.findAll();
        return ResponseEntity.ok(usuarioss);
    }
}
