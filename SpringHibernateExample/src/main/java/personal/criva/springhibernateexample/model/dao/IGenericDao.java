package personal.criva.springhibernateexample.model.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao <T extends Serializable>{

    public T save(T entity);

    public T update(T entity);

    public <K> T findById(K id);

    public List<T> findByProperty(String property, Object value);
}
