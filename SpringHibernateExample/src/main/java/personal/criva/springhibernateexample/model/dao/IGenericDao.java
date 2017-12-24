package personal.criva.springhibernateexample.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IGenericDao <T extends Serializable, K>{

    public T save(T entity);

    public T update(T entity);
    
    public void delete(T entity);
    
    public void delete(Collection<T> entities);
    
    public void deleteAll();
    
    public void deleteById(String idAttributeName, K id);
    
    public T getReference(K id);

    public T findById(K id);
    
    public List<T> findAllById(String idAttributeName, List<K> values);

    public List<T> findByAttribute(String attributeName, Object value);
    
    public List<T> findAll();
}
