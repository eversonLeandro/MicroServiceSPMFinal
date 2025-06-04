package co.edu.unicauca.interfaces;

import java.util.List;


public interface IRepository {
    boolean save(Object entity);
    boolean update(Object entity);
    boolean delete(int id);
    List<Object> list();
    Object found(Object usename);
}
