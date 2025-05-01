package co.edu.unicauca.companymicroservice.Service;

import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Repositories.ContactoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService implements BaseService<Contacto>{

    @Autowired
    private ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @Override
    @Transactional
    public List<Contacto> findAll() throws Exception {
        try{
            List<Contacto> entities = contactoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Contacto findById(String ID) throws Exception {
        try{
            Optional<Contacto> entityOptional = contactoRepository.findById(Long.valueOf(ID));
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean save(Contacto entity) throws Exception {
        try{
            entity = contactoRepository.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(Long id, Contacto entity) throws Exception {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        return false;
    }

}
