package personal.criva.springhibernateexample.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

public interface IGenericDao<T extends Serializable, K> {

    public T save(T entity);

    public T update(T entity);

    public void delete(T entity);

    public void delete(Collection<T> entities);

    public void deleteAll();

    public void deleteById(String idAttributeName, K id);

    public void refresh(T entity);
    
    public void flush();
    
    public void clear();
    
    public T getReference(K id);

    public T findById(K id);

    public List<T> findAllById(String idAttributeName, List<K> values);

    public List<T> findAll();

    public List<T> findAll(String orderByAttribute);

    public Long countByAttribute(String attributeName, Object value);

    public List<T> findByAttribute(String attributeName, Object value);

    public List<T> findByAttribute(String attributeName, Object value, List<String> orderByAttributes);

    public List<T> findByAttributes(List<String> attributesNames, List<Object> values);

    public List<T> findByAttributes(List<String> attributesNames, List<Object> values, List<String> orderByAttributes);
    
    public List<T> findByNativeQuery(String nativeQueryName, Object... values);

    public List<T> findByNamedQuery(String namedQueryName, Hashtable<?, ?> values);
    
    public int executeByNamedQuery(String namedQueryName, Hashtable<?, ?> values);
}
