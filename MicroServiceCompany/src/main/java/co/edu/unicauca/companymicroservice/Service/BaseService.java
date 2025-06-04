package co.edu.unicauca.companymicroservice.Service;

import java.util.List;

public interface BaseService<E> {
    public List<E> findAll() throws Exception;
    public E findById(String ID) throws Exception;
    public boolean save(E entity) throws Exception;
    //sin uso
    public boolean update(Long id,E entity) throws Exception;
    public boolean delete(Long id) throws Exception;

}
