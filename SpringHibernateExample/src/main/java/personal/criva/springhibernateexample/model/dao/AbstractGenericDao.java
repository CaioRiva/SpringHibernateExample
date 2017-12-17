package personal.criva.springhibernateexample.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractGenericDao<T extends Serializable> implements IGenericDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractGenericDao() {

	this.entityClass = (Class<T>) ((ParameterizedType) this.getClass()
		.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T entity) {

	try {

	    entityManager.persist(entity);

	    entityManager.refresh(entity);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return entity;

    }

    public T update(T entity) {

	T updatedEntity = null;

	try {

	    updatedEntity = entityManager.merge(entity);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return updatedEntity;
    }

    public <K> T findById(K id) {

	T foundEntity = null;

	try {

	    foundEntity = entityManager.find(entityClass, id);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundEntity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findByProperty(String property, Object value) {

	List<T> queryResult = null;

	try {

	    StringBuilder query = new StringBuilder(
		    "SELECT e FROM " + entityClass.getName() 
		    	+ " e WHERE e." + property + " = :value");

	    queryResult = entityManager.createQuery(query.toString())
		    .setParameter("value", value).getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;
    }

}
