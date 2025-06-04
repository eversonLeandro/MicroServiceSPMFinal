package co.edu.unicauca.studentmicroservice.Service;

import co.edu.unicauca.studentmicroservice.Entities.Usuario;
import co.edu.unicauca.studentmicroservice.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public List<Usuario> findAll() throws Exception {
        try{
            List<Usuario> entities = usuarioRepository.findAll();
            return  entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Usuario findById(String ID) throws Exception {
        try{
            Optional<Usuario> entityOptional = usuarioRepository.findById(ID);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean save(Usuario entity) throws Exception {
        try{
            entity = usuarioRepository.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean update(Long id, Usuario entity) throws Exception {
        return false;
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
